package com.github.elliothb.daocc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.elliothb.daocc.xml.DataTypeXml;
import com.github.elliothb.daocc.xml.SchemaXml;
import com.github.elliothb.daocc.xml.TableColumnXml;
import com.github.elliothb.daocc.xml.TableXml;

public class DaoValidation {
    private static final Pattern VALID_SCHEMA_NAME = Pattern.compile("[a-zA-Z]\\w*");

    private static boolean matches(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public static void validateSchema(SchemaXml schema) throws DaoCompilerException {
        if (!matches(VALID_SCHEMA_NAME, schema.name)) {
            throw new DaoCompilerException("Invalid schema name '%s'", schema.name);
        }

        if (schema.tables.isEmpty()) {
            throw new DaoCompilerException("Schema '%s' must contain at least one table", schema.name);
        }

        for (TableXml table : schema.tables) {
            validateTable(table, schema);
        }
    }

    public static void validateTable(TableXml table, SchemaXml schema) throws DaoCompilerException {
        if (!matches(VALID_SCHEMA_NAME, table.name)) {
            throw new DaoCompilerException("Invalid table name '%s' in schema '%s'", table.name, schema.name);
        }

        if (table.columns.isEmpty()) {
            throw new DaoCompilerException("Table '%s' in schema '%s' must contain at least one column", table.name, schema.name);
        }

        for (TableColumnXml column : table.columns) {
            validateTableColumn(column, table);
        }
    }

    public static void validateTableColumn(TableColumnXml column, TableXml table) throws DaoCompilerException {
        if (!matches(VALID_SCHEMA_NAME, column.name)) {
            throw new DaoCompilerException("Invalid column name '%s' in table '%s'", column.name, table.name);
        }

        boolean sizedType = column.type.equals(DataTypeXml.VARCHAR) || column.type.equals(DataTypeXml.CHAR);

        if (sizedType && column.length <= 0) {
            throw new DaoCompilerException("Column '%s' in table '%s' has invalid length %d", column.name, table.name, column.length);
        }
    }
}
