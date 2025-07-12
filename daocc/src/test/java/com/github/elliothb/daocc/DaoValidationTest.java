package com.github.elliothb.daocc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.elliothb.daocc.xml.AutoIncrementXml;
import com.github.elliothb.daocc.xml.DataTypeXml;
import com.github.elliothb.daocc.xml.SchemaXml;
import com.github.elliothb.daocc.xml.TableColumnXml;
import com.github.elliothb.daocc.xml.TableXml;

public class DaoValidationTest {
    private SchemaXml validSchema;
    private TableXml validTable;

    @BeforeEach
    public void before() {
        TableColumnXml idColumn = new TableColumnXml();
        idColumn.name = "id";
        idColumn.type = DataTypeXml.ULONG;
        idColumn.nullable = false;
        idColumn.autoIncrement = AutoIncrementXml.DEFAULT;

        validTable = new TableXml();
        validTable.name = "validTable";
        validTable.columns = List.of(idColumn);

        validSchema = new SchemaXml();
        validSchema.name = "validSchema";
        validSchema.tables = List.of(validTable);
    }

    @Test
    @DisplayName("Accept valid schema")
    public void testValidSchema() throws Exception {
        DaoValidation.validateSchema(validSchema);
    }

    @ParameterizedTest
    @DisplayName("Reject schema with invalid name")
    @ValueSource(strings = {"invalid schema name", "123schema", "schema-name"})
    public void testSchemaInvalidName() {
        SchemaXml schema = new SchemaXml();
        schema.name = "invalid name";
        DaoCompilerException exception = Assertions.assertThrows(
            DaoCompilerException.class,
            () -> DaoValidation.validateSchema(schema)
        );
        Assertions.assertTrue(exception.getMessage().contains(schema.name));
    }

    @Test
    @DisplayName("Reject schema with no tables")
    public void testSchemaNoTables() {
        SchemaXml schema = new SchemaXml();
        schema.name = "emptySchema";
        schema.tables = List.of();

        DaoCompilerException exception = Assertions.assertThrows(
            DaoCompilerException.class,
            () -> DaoValidation.validateSchema(schema)
        );
        Assertions.assertTrue(exception.getMessage().contains(schema.name));
    }

    @ParameterizedTest
    @DisplayName("Reject table with invalid name")
    @ValueSource(strings = {"invalid name", "123table", "table-name"})
    public void testTableInvalidName() {
        TableXml table = new TableXml();
        table.name = "invalid name";

        DaoCompilerException exception = Assertions.assertThrows(
            DaoCompilerException.class,
            () -> DaoValidation.validateTable(table, validSchema)
        );
        Assertions.assertTrue(exception.getMessage().contains(validSchema.name));
        Assertions.assertTrue(exception.getMessage().contains(table.name));
    }
}
