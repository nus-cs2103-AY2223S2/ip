package duke.task;

import duke.exception.DukeException;

public class TaskSaveException extends DukeException {
    public TaskSaveException(String errorMsg) {
        super(errorMsg);
    }
}
