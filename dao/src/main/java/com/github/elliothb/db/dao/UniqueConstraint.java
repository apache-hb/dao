package com.github.elliothb.db.dao;

import java.util.List;

public class UniqueConstraint {
    private final String name;
    private final List<Column> columns;

    public UniqueConstraint(String name, List<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return columns;
    }
}
