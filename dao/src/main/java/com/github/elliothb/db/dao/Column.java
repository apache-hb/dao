package com.github.elliothb.db.dao;

import java.util.Map;

public class Column extends NamedObject {
    private final DataType type;
    private final int size;
    private final boolean required;

    public Column(String name, DataType type, int size, boolean nullable, String comment,
            Map<String, String> attributes) {
        super(name, comment, attributes);
        this.type = type;
        this.size = size;
        this.required = nullable;
    }

    public DataType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public boolean isRequired() {
        return required;
    }
}
