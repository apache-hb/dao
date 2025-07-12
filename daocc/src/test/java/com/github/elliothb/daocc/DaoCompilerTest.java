package com.github.elliothb.daocc;

import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.elliothb.daocc.xml.DataTypeXml;
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
        Assertions.assertFalse(column.nullable);
    }
}
