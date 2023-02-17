package duke.task;

import duke.exception.DukeException;

/**
 * TaskSaveException is thrown when an issue occurs when saving/loading tasks.
 */
public class TaskSaveException extends DukeException {
    public TaskSaveException(String errorMsg) {
        super(errorMsg);
    }
}
