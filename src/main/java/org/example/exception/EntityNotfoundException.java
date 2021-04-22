package org.example.exception;

public class EntityNotfoundException extends RuntimeException {
    public EntityNotfoundException(String format) {
        super(format);
    }
}
