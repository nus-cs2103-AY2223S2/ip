package enums;

import java.util.Objects;

import exceptions.DukeException;


/**
 * Enum types for Task.
 */
public enum TaskType {
    TODO("todo", new DukeException("Your format is incorrect.\n"
            + "Please try again with the following format: todo [description].")),
    EVENT("event", new DukeException("Your format is incorrect.\n"
            + "Please try again with the following format: event [description] /from [yyyy-mm-dd] /to [yyyy-mm-dd].")),
    DEADLINE("deadline", new DukeException("Your format is incorrect.\n"
            + "Please try again with the following format: deadline [description] /by [yyyy-mm-dd].")),
    ALL("*", new DukeException("Your format is incorrect."));

    /** type of task **/
    private final String type;
    /** exception to be thrown when an error occurs **/
    private final DukeException err;

    TaskType(String type, DukeException err) {
        this.type = type;
        this.err = err;
    }

    public String getType() {
        return this.type;
    }

    public DukeException getErr() {
        return err;
    }

    /**
     * Checks if a task type is the default ALL value.
     *
     * @return boolean value
     */
    public boolean isAll() {
        return Objects.equals(this.getType(), "*");
    }
}
