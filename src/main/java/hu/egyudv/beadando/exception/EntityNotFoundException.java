package hu.egyudv.java.exception;

public class EntityNotfoundException extends RuntimeException {
    public EntityNotfoundException(String format) {
        super(format);
    }
}
