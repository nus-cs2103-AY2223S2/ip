package duke;

/**
 * Handles exceptions specific to Duke
 */
public class DukeException extends Exception {

    /**
     * Holds the error message of the exception
     *
     * @param errorMessage Error message of the exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public static DukeException getError() {
        return new DukeException("There was an unknown error");
    }

    public static DukeException getErrorParse() {
        return new DukeException("I'm sorry, but I don't know what that means");
    }

    public static DukeException getErrorStorage(String type) {
        return new DukeException("Failed to " + type + " data due to I/O interrupt");
    }

    public static DukeException getErrorTaskType() {
        return new DukeException("Task is of the wrong type");
    }

    public static DukeException getErrorTaskIndexOutOfBounds(String type) {
        return new DukeException("Index of task to be " + type + " is not in the TaskList");
    }

    public static DukeException getErrorTaskLoadMissingField() {
        return new DukeException("Saved data is missing some fields");
    }

    public static DukeException getErrorTaskMissingField(String type) {
        if (type.equalsIgnoreCase("todo")) {
            return new DukeException("The description of a todo cannot be empty.");
        }
        return new DukeException(type + " task is missing some fields.");
    }

    public static DukeException getErrorTaskTimeFormat(String task, String time) {
        return new DukeException("The " + time + " of " + task + " task must be in the format <YYYY-MM-DD>.");
    }

    public static DukeException getErrorTaskDuplicate() {
        return new DukeException("Task is already in the TaskList");
    }

    public static DukeException getErrorTaskNumberFormat() {
        return new DukeException("Index given is not an integer");
    }

    public static DukeException getErrorTaskArrayIndexOutOfBounds() {
        return new DukeException("An index for a task was not given");
    }

    public static DukeException getErrorTaskMissingKeyword() {
        return new DukeException("A keyword was not given");
    }
}
