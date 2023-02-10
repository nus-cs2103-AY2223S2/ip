package duke.exceptions;

public class IOException extends Exception {
    public IOException(String message) {
        super(message);
    }

    public IOException(String message, Throwable cause) {
        super(message, cause);
    }
}
