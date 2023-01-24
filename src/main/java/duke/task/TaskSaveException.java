package duke.task;

import duke.DukeException;

public class TaskSaveException extends DukeException {
    public TaskSaveException(String errorMsg) {
        super(errorMsg);
    }
}
