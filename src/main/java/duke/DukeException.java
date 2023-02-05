package duke;

/**
 * The DukeException extends Exception containing the message to throw when faces exception
 * The subclasses contains different messages for different exceptions when thrown
 */
public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

class TaskNotExist extends DukeException {
    public TaskNotExist() {
        super("Invalid value, there isn't this much tasks in the list :-( ");
    }
}

class MissingDescription extends DukeException {
    public MissingDescription() {
        super("OOPS!!! The description of a task cannot be empty");
    }
}

