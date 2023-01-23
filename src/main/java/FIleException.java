public class FIleException extends DukeException {
    public FIleException() {
    }

    public FIleException(String message) {
        super(message);
    }

    public FIleException(String message, Throwable cause) {
        super(message, cause);
    }

    public FIleException(Throwable cause) {
        super(cause);
    }

    public FIleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "Error with importing the file";
    }
}
