package duke.exception;

public enum ERROR {
    TODO_EMPTY("OOPS!!! The description of a todo cannot be empty."),
    DEADLINE_EMPTY("OOPS!!! The description of a deadline cannot be empty."),
    EVENT_EMPTY("OOPS!!! The description of an event cannot be empty."),
    DEADLINE_WRONG_FORMAT("OOPS!!! Deadline must have the format \"deadline <description> /by <date>\""),
    EVENT_WRONG_FORMAT("OOPS!!! Event must have the format \"event <description> /from <date> /to <date>\""),
    INVALID_INPUT("OOPS!!! I'm sorry, but I don't know what that means :-("),
    INVALID_INDEX("Invalid index!\nThere are only %d tasks in the list."),
    CORRUPTED_TASK_DATA("The task data file may have been corrupted.");

    private final String message;

    ERROR(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
