package com.github.elliothb.daocc;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import com.github.elliothb.daocc.xml.DataTypeXml;
import com.github.elliothb.daocc.xml.SchemaXml;
import com.github.elliothb.daocc.xml.TableColumnXml;
import com.github.elliothb.daocc.xml.TableXml;

public class DaoGenerator {
    public static String pascalCase(String name) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpper = true;
        for (char c : name.toCharArray()) {
            if (c == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    sb.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }

        return sb.toString();
    }

    public static String camelCase(String name) {
        String pascal = pascalCase(name);
        return Character.toLowerCase(pascal.charAt(0)) + pascal.substring(1);
    }

    private static void generateDaoColumn(TableXml tableXml, TableColumnXml columnXml, StringBuilder sb) throws Exception {

    }

    public static void generateDaoClass(SchemaXml schemaXml, TableXml tableXml, OutputStream output) throws Exception {
        String javaPackage = schemaXml.javaPackage;
        String className = pascalCase(tableXml.name) + "Dao";

        Set<String> imports = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        sb.append("public class ").append(className).append(" {\n");
        for (TableColumnXml column : tableXml.columns) {
            generateDaoColumn(tableXml, column, sb);
        }
    }
}
