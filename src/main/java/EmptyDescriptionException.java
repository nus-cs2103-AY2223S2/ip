public class EmptyDescriptionException extends HomieException {
    public EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "    > where's the description homie?";
    }
}
