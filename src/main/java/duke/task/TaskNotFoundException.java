package duke.task;

import duke.exception.DukeException;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
