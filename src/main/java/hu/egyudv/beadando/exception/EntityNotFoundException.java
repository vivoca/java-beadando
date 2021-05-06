package hu.egyudv.beadando.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String format) {
        super(format);
    }
}
