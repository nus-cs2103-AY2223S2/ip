package duke.exception.editcommandexceptions;

import duke.exception.DukeException;

/**
 * Thrown when the task number stated is not found in the task list of Duke for a mark or unmark command.
 */
public class EditTaskNumberInvalidException extends DukeException {
    /**
     * Thrown when the task number stated is not found in the task list of Duke for a mark or unmark command..
     */
    public EditTaskNumberInvalidException() {
        super("\n"
                + "Either there is no task with that number or you didn't format your command correctly!" + "\n"
                + "Use \"edit {task number} {new details}\" where task number is the number of that task "
                + "and new details are the new details of that task." + "\n");
    }
}
