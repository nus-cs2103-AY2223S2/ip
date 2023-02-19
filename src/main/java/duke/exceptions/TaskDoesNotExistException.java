package duke.exceptions;

public class TaskDoesNotExistException extends Exception {

    public TaskDoesNotExistException() {
        super("Apologies, however this task does not exist");
    }

    public TaskDoesNotExistException(String message) {
        super(message);
    }
}

