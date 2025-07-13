package com.github.elliothb.daocc;

import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.elliothb.daocc.xml.ConstraintXml;
import com.github.elliothb.daocc.xml.DataTypeXml;
import com.github.elliothb.daocc.xml.ListColumnXml;
import com.github.elliothb.daocc.xml.SchemaXml;
import com.github.elliothb.daocc.xml.TableColumnXml;
import com.github.elliothb.daocc.xml.TableXml;

public class DaoCompilerTest {
    @Test
    @DisplayName("Parse simple schema")
    public void testSimpleSchema() throws Exception {
        SchemaXml schema = DaoCompiler.parse(Path.of("src/test/resources/schemas/simple.xml"));
        Assertions.assertNotNull(schema);
        Assertions.assertEquals("simple", schema.name);
        Assertions.assertEquals(1, schema.tables.size());

        TableXml table = schema.tables.get(0);
        Assertions.assertEquals("region", table.name);
        Assertions.assertEquals(1, table.columns.size());

        TableColumnXml column = table.columns.get(0);
        Assertions.assertEquals("name", column.name);
        Assertions.assertEquals(DataTypeXml.VARCHAR, column.type);
        Assertions.assertEquals(16, column.length);
        Assertions.assertTrue(column.required);
    }

    @Test
    @DisplayName("Parse schema with constraints")
    public void testConstraintsSchema() throws Exception {
        SchemaXml schema = DaoCompiler.parse(Path.of("src/test/resources/schemas/constraints.xml"));
        Assertions.assertNotNull(schema);
        Assertions.assertEquals("constraints", schema.name);
        Assertions.assertEquals(1, schema.tables.size());

        TableXml table = schema.tables.get(0);
        Assertions.assertEquals("region", table.name);
        Assertions.assertEquals(1, table.columns.size());

        TableColumnXml column = table.columns.get(0);
        Assertions.assertEquals("name", column.name);
        Assertions.assertEquals(DataTypeXml.VARCHAR, column.type);
        Assertions.assertEquals(16, column.length);
        Assertions.assertTrue(column.required);

        ConstraintXml constraint = column.constraints.get(0);
        Assertions.assertNotNull(constraint);
        Assertions.assertEquals("ak_name", constraint.name);
        Assertions.assertTrue(constraint.unique);
    }

    @Test
    @DisplayName("Parse schema with list")
    public void testListSchema() throws Exception {
        SchemaXml schema = DaoCompiler.parse(Path.of("src/test/resources/schemas/list.xml"));
        Assertions.assertNotNull(schema);
        Assertions.assertEquals("listSchema", schema.name);
        Assertions.assertEquals(2, schema.tables.size());

        TableXml customerTable = schema.tables.get(0);
        Assertions.assertEquals("customer", customerTable.name);
        Assertions.assertEquals(1, customerTable.columns.size());
        Assertions.assertEquals(1, customerTable.lists.size());

        TableColumnXml customerIdColumn = customerTable.columns.get(0);
        Assertions.assertEquals("id", customerIdColumn.name);
        Assertions.assertEquals(DataTypeXml.CHAR, customerIdColumn.type);
        Assertions.assertEquals(12, customerIdColumn.length);
        Assertions.assertTrue(customerIdColumn.required);

        ListColumnXml resourceList = customerTable.lists.get(0);
        Assertions.assertEquals("resources", resourceList.name);
        Assertions.assertEquals("resource", resourceList.table);
        Assertions.assertEquals("id", resourceList.column);

        TableXml resourceTable = schema.tables.get(1);
        Assertions.assertEquals("resource", resourceTable.name);
        Assertions.assertEquals(4, resourceTable.columns.size());
    }
}
