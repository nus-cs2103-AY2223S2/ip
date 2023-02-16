package duke.exception.tasklistexceptions;

import duke.exception.DukeException;

/**
 * Thrown when an invalid task number is passed into a TaskList method.
 */
public class TaskListTaskNumberInvalidException extends DukeException {
    /**
     * Thrown when an invalid task number is passed into a TaskList method.
     */
    public TaskListTaskNumberInvalidException() {
        super("\n"
                + "I don't think theres a task with that number!" + "\n");
    }
}
