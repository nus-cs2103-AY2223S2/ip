package duke.util.service;

import duke.util.Task;

/**
 * Construct a {@code Deadline} with the
 * deadline and action specified by the user
 */

public class ToDo extends Task {

    /**
     * Construct a {@code Deadline} with the action specified
     * by the user
     */

    public ToDo(String action) {
        super("T", action);
    }

    public ToDo(String action, boolean isDone) {
        super("T", action, isDone);
    }
}
