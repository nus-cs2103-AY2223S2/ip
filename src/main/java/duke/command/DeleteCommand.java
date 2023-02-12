package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command for deletion of tasks.
 */
public class DeleteCommand {
    /**
     * Deletes a task from a list of tasks.
     * @param tasks the list of tasks.
     * @param input user's input.
     * @return acknowledgement of the task being successfully deleted.
     * @throws DukeException if an invalid task number were found.
     */
    public static String executeDelete(TaskList tasks, String input) throws DukeException {
        if (input.length() < 8 || !input.matches("delete\\s+\\d+")) {
            throw new DukeException("<delete> command should receive a task number!");
        }
        int taskNum = Integer.parseInt(input.substring(7).trim());
        if (1 <= taskNum && taskNum <= tasks.getSize()) {
            Task removed = tasks.delete(taskNum);
            return "Noted. I've removed this task:\n\t"
                    + removed.getStatusIcon()
                    + "\n" + "Now you have "
                    + tasks.getSize()
                    + " task(s) in the list.";
        } else {
            throw new InvalidIndexException(taskNum);
        }
    }
}
