package duke.exception.marktaskexceptions;

import duke.exception.DukeException;

/**
 * Thrown when the task number stated is not found in the task list of Duke for a mark or unmark command.
 */
public class MarkTaskNumberInvalidException extends DukeException {
    /**
     * Thrown when the task number stated is not found in the task list of Duke for a mark or unmark command..
     */
    public MarkTaskNumberInvalidException() {
        super("\n"
                + "Either there is no task with that number or you didn't format your command correctly!" + "\n"
                + "Use \"mark {task number}\" or \"unmark {task number}\" where task number is the number of that task."
                + "\n");
    }
}
