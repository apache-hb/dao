package com.github.elliothb.db.dao;

import java.util.Map;
import java.util.Objects;

public abstract class NamedObject {
    private final String name;
    private final String comment;
    private final Map<String, String> attributes;

    public NamedObject(String name, String comment, Map<String, String> attributes) {
        this.name = Objects.requireNonNull(name);
        this.comment = comment;
        this.attributes = Objects.requireNonNull(attributes);
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }
}
