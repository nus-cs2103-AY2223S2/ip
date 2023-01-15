public class EmptyDescriptionException extends Exception {
    EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
