package duke.task;

import duke.exception.DukeException;

/**
 * TaskNotFoundException is thrown when a task is not found.
 */
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
