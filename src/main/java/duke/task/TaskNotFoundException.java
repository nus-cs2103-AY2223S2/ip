package duke.task;

import duke.DukeException;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
