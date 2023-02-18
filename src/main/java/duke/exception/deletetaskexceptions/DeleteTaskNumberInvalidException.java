package duke.exception.deletetaskexceptions;

import duke.exception.DukeException;

/**
 * Thrown when the task number stated is not found in the task list of Duke for a delete command.
 */
public class DeleteTaskNumberInvalidException extends DukeException {
    /**
     * Thrown when the task number stated is not found in the task list of Duke for a delete command.
     */
    public DeleteTaskNumberInvalidException() {
        super("\n"
                + "Either there is no task with that number or you didn't format your command correctly!" + "\n"
                + "Use \"delete {task number}\" where task number is the number of that task." + "\n");
    }
}
