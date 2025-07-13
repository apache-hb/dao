package com.github.elliothb.db.dao;

public class ForeignConstraint {
    private final String name;
    private final Column column;
    private final Table foreignTable;
    private final Column foreignColumn;

    public ForeignConstraint(String name, Column column, Table foreignTable, Column foreignColumn) {
        this.name = name;
        this.column = column;
        this.foreignTable = foreignTable;
        this.foreignColumn = foreignColumn;
    }

    public String getName() {
        return name;
    }

    public Column getColumn() {
        return column;
    }

    public Table getForeignTable() {
        return foreignTable;
    }

    public Column getForeignColumn() {
        return foreignColumn;
    }
}
