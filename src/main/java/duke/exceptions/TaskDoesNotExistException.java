package duke.exceptions;

public class TaskDoesNotExistException extends Exception {

    public TaskDoesNotExistException() {
        super("Task doesn't exist bruh");
    }

    public TaskDoesNotExistException(String message) {
        super(message);
    }
}

