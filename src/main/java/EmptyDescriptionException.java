public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
        super("Invalid input. The description cannot be empty.");
    }
}
