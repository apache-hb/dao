package com.github.elliothb.db.spi;

public interface DaoEnvironment extends AutoCloseable {
    default public DaoConnection connect() throws Exception {
        throw new UnsupportedOperationException();
    }
}
