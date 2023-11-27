package at.spengergasse.efees.exception;

public final class NotValidException extends RuntimeException {
    public NotValidException(String message) {
        super(message);
    }
}
