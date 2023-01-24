public class EmptyBodyException extends IllegalArgumentException {
    public EmptyBodyException(String message) {
        super(message);
    }

    public EmptyBodyException() {
        super();
    }
}
