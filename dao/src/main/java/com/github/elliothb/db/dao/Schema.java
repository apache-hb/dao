package com.github.elliothb.db.dao;

import java.util.List;
import java.util.Map;

public class Schema extends NamedObject {
    private final List<Table> tables;

    public Schema(String name, List<Table> tables, Map<String, String> attributes) {
        super(name, null, attributes);
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    public Table findTable(String name) {
        return tables.stream()
                .filter(table -> table.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
