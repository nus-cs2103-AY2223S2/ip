public class EmptyDescriptionException extends  Exception {

    public EmptyDescriptionException() {
        super("bro where's the task description?");
    }

    public EmptyDescriptionException(String message) {
        super(message);
    }
}
