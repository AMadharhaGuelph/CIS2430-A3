package exceptions;

public class InvalidSaveFileException extends RuntimeException {
    public InvalidSaveFileException() {}

    public InvalidSaveFileException(String message) {
        super(message);
    }

    public InvalidSaveFileException(Throwable cause) {
        super(cause);
    }
    
    public InvalidSaveFileException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
