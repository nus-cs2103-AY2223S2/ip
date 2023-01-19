package enums;

import exceptions.DukeException;

/**
 * Enum for Task.
 */
public enum TaskType {
    TODO("todo", new DukeException("Your format is incorrect.\n" +
            "Please try again with the following format: todo [description].")),
    EVENT("event", new DukeException("Your format is incorrect.\n" +
            "Please try again with the following format: event [description] /from [yyyy-mm-dd] /to [yyyy-mm-dd].")),
    DEADLINE("deadline", new DukeException("Your format is incorrect.\n" +
            "Please try again with the following format: deadline [description] /by [yyyy-mm-dd]."));
    private final String type;
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
}
