package duke;

/**
 * Extends from Exception to handle different exceptions.
 * Different exceptions correspond to different message.
 */
public class DukeException extends Exception {
    private final String eMessage;

    /**
     * Generates a <code>DukeException</code> object.
     *
     * @param eMessage Respective error message for exception.
     */
    public DukeException(String eMessage) {
        this.eMessage = eMessage;
    }

    /**
     * Gets the error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return this.eMessage;
    }
}

class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String taskType) {
        super("Oh no, the description of " + taskType + " is missing/incomplete! Please try again.");
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

class CheckNotFindException extends DukeException {
    public CheckNotFindException() {
        super("On no, your check is in the wrong format! Please try again.");
    }
}

class NoSortTypeException extends DukeException {
    public NoSortTypeException() {
        super("You must specific what you want to sort, deadline or event. Please try again!");
    }
}
