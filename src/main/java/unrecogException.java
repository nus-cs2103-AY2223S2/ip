public class unrecogException extends Exception {
    public unrecogException(String message) {
        super(message);
    }

    public unrecogException(Throwable throwable) {
        super(throwable);
    }

    public unrecogException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
