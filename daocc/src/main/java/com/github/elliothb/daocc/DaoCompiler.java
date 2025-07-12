package com.github.elliothb.daocc;

import java.nio.file.Path;
import java.util.regex.Pattern;

import com.github.elliothb.daocc.xml.ConstraintXml;
import com.github.elliothb.daocc.xml.SchemaXml;
import com.github.elliothb.daocc.xml.TableColumnXml;
import com.github.elliothb.daocc.xml.TableXml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class DaoCompiler {
    public static SchemaXml parse(Path path) throws JAXBException, DaoCompilerException {
        JAXBContext jaxbContext = JAXBContext.newInstance(SchemaXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SchemaXml schema = (SchemaXml) unmarshaller.unmarshal(path.toFile());
        DaoValidation.validateSchema(schema);
        return schema;
    }

    public static String getConstraintName(TableXml table, TableColumnXml column, ConstraintXml constraint) {
        if (constraint.name != null) {
            return constraint.name;
        }

        if (constraint.unique) {
            return String.format("ak_%s_%s", table.name, column.name);
        }

        if (constraint.references != null) {
            String[] parts = constraint.references.split(Pattern.quote("."));
            return String.format("fk_%s_%s_to_%s_%s", table.name, column.name, parts[0], parts[1]);
        }

        throw new IllegalArgumentException("Constraint is neither a unique or foreign key constraint");
    }
}
