package com.github.elliothb.daocc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.elliothb.daocc.xml.DataTypeXml;
import com.github.elliothb.daocc.xml.SchemaXml;
import com.github.elliothb.daocc.xml.TableColumnXml;
import com.github.elliothb.daocc.xml.TableXml;

public class DaoClassGenerator {
    private static final Logger LOGGER = LogManager.getLogger(DaoClassGenerator.class);

    private final SchemaXml schemaXml;
    private final TableXml tableXml;
    private final Set<String> imports;
    private final StringBuilder classContent;

    public DaoClassGenerator(SchemaXml schemaXml, TableXml tableXml) {
        this.schemaXml = schemaXml;
        this.tableXml = tableXml;
        this.imports = new HashSet<>();
        this.classContent = new StringBuilder();

        generateClass();
    }

    public void transferTo(OutputStream output) throws IOException {
        String javaPackage = schemaXml.javaPackage;
        try (OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8)) {
            writer.write("package " + javaPackage + ";\n\n");

            for (String importStatement : imports) {
                writer.write("import " + importStatement + ";\n");
            }

            writer.write("\n");
            writer.write(classContent.toString());
        }
    }

    private void generateClass() {
        String className = DaoGenerator.pascalCase(tableXml.name) + "Dao";

        LOGGER.trace("Generating DAO class: {}.{}", schemaXml.javaPackage, className);

        List<TableColumnXml> requiredColumns = new ArrayList<>();

        // Collect all required columns
        for (TableColumnXml column : tableXml.columns) {
            if (column.required) {
                requiredColumns.add(column);
            }
        }

        // Start class definition
        classContent.append("public class ").append(className).append(" {\n");

        // Generate fields for each column
        for (TableColumnXml column : tableXml.columns) {
            generateColumnField(column);
        }

        classContent.append("\n");

        // Generate constructor for required columns
        if (!requiredColumns.isEmpty()) {
            classContent.append("    public ").append(className).append("(");
            classContent.append(requiredColumns.stream()
                .map(column -> daoJavaType(column) + " " + DaoGenerator.camelCase(column.name))
                .collect(Collectors.joining(", ")));
            classContent.append(") {\n");

            for (TableColumnXml column : requiredColumns) {
                generateNullCheck(column);
            }

            for (TableColumnXml column : requiredColumns) {
                String fieldName = DaoGenerator.camelCase(column.name);
                classContent.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
            }
            classContent.append("    }\n");
        }

        for (TableColumnXml column : tableXml.columns) {
            classContent.append("\n");
            generateColumnAccessors(column);
        }

        // Close class definition
        classContent.append("}\n");
    }

    private void generateColumnField(TableColumnXml columnXml) {
        String type = daoJavaType(columnXml);
        String fieldName = DaoGenerator.camelCase(columnXml.name);
        classContent.append("    private ").append(type).append(" ").append(fieldName).append(";\n");
    }

    private void generateColumnAccessors(TableColumnXml columnXml) {
        String type = daoJavaType(columnXml);
        String accessorName = DaoGenerator.pascalCase(columnXml.name);
        String fieldName = DaoGenerator.camelCase(columnXml.name);
        String getPrefix = columnXml.type == DataTypeXml.BOOL ? "is" : "get";
        classContent.append("    public ").append(type).append(" ").append(getPrefix).append(accessorName).append("() {\n");
        classContent.append("        return this.").append(fieldName).append(";\n");
        classContent.append("    }\n\n");

        generateColumnSetter(columnXml);
    }

    private void generateColumnSetter(TableColumnXml columnXml) {
        String type = daoJavaType(columnXml);
        String accessorName = DaoGenerator.pascalCase(columnXml.name);
        String fieldName = DaoGenerator.camelCase(columnXml.name);

        classContent.append("    public void set").append(accessorName).append("(").append(type).append(" ").append(fieldName).append(") {\n");

        generateNullCheck(columnXml);

        classContent.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
        classContent.append("    }\n");
    }

    private void generateNullCheck(TableColumnXml columnXml) {
        JavaColumnInfo columnInfo = JavaColumnInfo.fromDataType(columnXml.type);
        if (columnInfo.nullable && columnXml.required) {
            classContent.append("        if (").append(DaoGenerator.camelCase(columnXml.name)).append(" == null) {\n");
            classContent.append("            throw new IllegalArgumentException(\"Required column '").append(columnXml.name).append("' cannot be null\");\n");
            classContent.append("        }\n");
        }
    }

    private static record JavaColumnInfo(String optionalType, String requiredType, String javaImport, boolean nullable) {
        private static final JavaColumnInfo BOOLEAN = new JavaColumnInfo("Boolean", "boolean", null, false);
        private static final JavaColumnInfo INT = new JavaColumnInfo("Integer", "int", null, false);
        private static final JavaColumnInfo UINT = new JavaColumnInfo("Long", "long", null, false);
        private static final JavaColumnInfo LONG = new JavaColumnInfo("Long", "long", null, false);
        private static final JavaColumnInfo ULONG = new JavaColumnInfo("BigInteger", "BigInteger", "java.math.BigInteger", true);
        private static final JavaColumnInfo FLOAT = new JavaColumnInfo("Float", "float", null, false);
        private static final JavaColumnInfo DOUBLE = new JavaColumnInfo("Double", "double", null, false);
        private static final JavaColumnInfo VARCHAR = new JavaColumnInfo("String", "String", null, true);
        private static final JavaColumnInfo CHAR = new JavaColumnInfo("char[]", "char[]", null, true);
        private static final JavaColumnInfo DATETIME = new JavaColumnInfo("LocalDateTime", "LocalDateTime", "java.time.LocalDateTime", true);
        private static final JavaColumnInfo TZ_DATETIME = new JavaColumnInfo("ZonedDateTime", "ZonedDateTime", "java.time.ZonedDateTime", true);
        private static final JavaColumnInfo BLOB = new JavaColumnInfo("ByteBuffer", "ByteBuffer", "java.nio.ByteBuffer", true);

        public static JavaColumnInfo fromDataType(DataTypeXml type) {
            return switch (type) {
                case BOOL -> BOOLEAN;
                case INT -> INT;
                case UINT -> UINT;
                case LONG -> LONG;
                case ULONG -> ULONG;
                case FLOAT -> FLOAT;
                case DOUBLE -> DOUBLE;
                case VARCHAR -> VARCHAR;
                case CHAR -> CHAR;
                case DATETIME -> DATETIME;
                case TZ_DATETIME -> TZ_DATETIME;
                case BLOB -> BLOB;
                default -> throw new IllegalArgumentException("Unsupported data type: " + type);
            };
        }
    }

    private String daoJavaType(TableColumnXml columnXml) {
        JavaColumnInfo columnInfo = JavaColumnInfo.fromDataType(columnXml.type);
        if (columnInfo.javaImport != null) {
            imports.add(columnInfo.javaImport);
        }
        return columnXml.required ? columnInfo.requiredType : columnInfo.optionalType;
    }
}
