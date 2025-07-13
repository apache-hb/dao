package com.github.elliothb.db.dao;

import java.util.List;
import java.util.Map;

public class Table extends NamedObject {
    private final Column primaryKey;
    private final List<Column> columns;
    private final List<ForeignConstraint> foreignConstraints;
    private final List<UniqueConstraint> uniqueConstraints;

    public Table(String name, Column primaryKey, List<Column> columns,
            List<ForeignConstraint> foreignConstraints,
            List<UniqueConstraint> uniqueConstraints,
            String comment, Map<String, String> attributes) {
        super(name, comment, attributes);
        this.primaryKey = primaryKey;
        this.columns = columns;
        this.foreignConstraints = foreignConstraints;
        this.uniqueConstraints = uniqueConstraints;
    }

    public Column getPrimaryKey() {
        return primaryKey;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<ForeignConstraint> getForeignConstraints() {
        return foreignConstraints;
    }

    public List<UniqueConstraint> getUniqueConstraints() {
        return uniqueConstraints;
    }

    public Column findColumn(String name) {
        return columns.stream()
                .filter(column -> column.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
