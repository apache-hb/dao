package com.github.elliothb.daocc;

public class DaoCompilerException extends Exception {
    public DaoCompilerException(String message) {
        super(message);
    }

    public DaoCompilerException(Throwable cause) {
        super(cause);
    }

    public DaoCompilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoCompilerException(String message, Object... args) {
        this(String.format(message, args));
    }

    public DaoCompilerException(Throwable cause, String message, Object... args) {
        this(String.format(message, args), cause);
    }
}
