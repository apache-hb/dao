package com.github.elliothb.db.spi;

import java.util.stream.Stream;

import com.github.elliothb.db.dao.Schema;

public interface DaoConnection extends AutoCloseable {
    default public void create(Schema schema) throws Exception {
        throw new UnsupportedOperationException();
    }

    default public <T> T selectOne() throws Exception {
        throw new UnsupportedOperationException();
    }

    default public <T> Stream<T> select() throws Exception {
        throw new UnsupportedOperationException();
    }
}
