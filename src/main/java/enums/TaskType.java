package enums;

import exceptions.DukeException;

public enum TaskType {
    TODO("todo", new DukeException("Your format is incorrect.\n" +
            "Please try again with the following format: todo [description].")),
    EVENT("event", new DukeException("Your format is incorrect.\n" +
            "Please try again with the following format: event [description] \\from [date] \\to [date].")),
    DEADLINE("deadline", new DukeException("Your format is incorrect.\n" +
            "Please try again with the following format: deadline [description] \\by [date]."));
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
