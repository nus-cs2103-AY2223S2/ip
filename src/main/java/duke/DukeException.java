package duke;

/**
 * Extend from Exception to handle different exceptions.
 * Different exceptions correspond to different message.
 */
public class DukeException extends Exception {
    public String eMessage;

    /**
     * Generate a <code>DukeException</code> object.
     *
     * @param eMessage Respective error message for exception.
     */
    public DukeException(String eMessage) {
        this.eMessage = eMessage;
    }
}

class UnknownInputException extends DukeException {
    public UnknownInputException() {
        super("Oh no, I am not sure what that means, could you try again?");
    }
}

class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String taskType) {
        super("Oh no, the description of a " + taskType + " cannot be empty! Please try again.");
    }
}

class MissingNumberException extends DukeException {
    public MissingNumberException(String operationType) {
        super("Oh no, the " + operationType + " must specific the task number! Please try again.");
    }
}

class TaskNotExistException extends DukeException {
    public TaskNotExistException() {
        super("Oh no, the task is not exist yet! Please try again.");
    }
}
