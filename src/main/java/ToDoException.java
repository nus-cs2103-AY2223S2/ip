public class ToDoException extends Exception {
    public ToDoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public ToDoException(String message) {
        super(message);
    }
}
