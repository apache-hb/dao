package com.github.elliothb.daocc;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.github.elliothb.daocc.xml.SchemaXml;

public class DaoGeneratorTest {
    @Test
    public void testGenerateDao() throws Exception {
        SchemaXml schema = DaoCompiler.parse(Path.of("src/test/resources/schemas/simple.xml"));
        DaoClassGenerator generator = new DaoClassGenerator(schema, schema.tables.get(0));

        Files.createDirectories(Path.of("target/test-work/dao"));
        try (OutputStream output = Files.newOutputStream(Path.of("target/test-work/dao/SimpleDao.java"))) {
            generator.transferTo(output);
        }
    }

    @Test
    public void testGenerateTypesDao() throws Exception {
        SchemaXml schema = DaoCompiler.parse(Path.of("src/test/resources/schemas/types.xml"));
        DaoClassGenerator generator = new DaoClassGenerator(schema, schema.tables.get(0));

        Files.createDirectories(Path.of("target/test-work/dao"));
        try (OutputStream output = Files.newOutputStream(Path.of("target/test-work/dao/RegionDao.java"))) {
            generator.transferTo(output);
        }
    }
}
