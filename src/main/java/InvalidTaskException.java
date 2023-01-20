public class InvalidTaskException extends Exception {

    public InvalidTaskException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public InvalidTaskException(String message) {
        super(message);
    }
}
