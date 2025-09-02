package concesionaria.exception;

public class DuplicateException extends Exception {
    public DuplicateException(String message) {
        super(message);
    }
    
    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
