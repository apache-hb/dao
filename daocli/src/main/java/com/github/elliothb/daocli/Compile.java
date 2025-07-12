package com.github.elliothb.daocli;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.elliothb.daocc.xml.SchemaXml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "compile",
    mixinStandardHelpOptions = true,
    description = "Compile a DAO file into source code"
)
public class Compile implements Runnable {
    private static final Logger LOG = LogManager.getLogger(Compile.class);

    private static SchemaXml loadSchema(Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(SchemaXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (SchemaXml) unmarshaller.unmarshal(path.toFile());
    }

    @Option(
        names = {"-s", "--schema"},
        description = "Path to the schema XML file",
        required = true
    )
    private Path schemaPath;

    @Override
    public void run() {
        try {
            LOG.info("Loading schema from {}", schemaPath);
            SchemaXml schema = loadSchema(schemaPath);
            LOG.info("Schema loaded successfully: {}", schema);
        } catch (JAXBException e) {
            LOG.catching(e);
        }
    }
}
