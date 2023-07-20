package org.example.CRUD;

public class CrudOperationException extends Exception {
    public CrudOperationException(String message) {
        super(message);
    }

    public CrudOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
