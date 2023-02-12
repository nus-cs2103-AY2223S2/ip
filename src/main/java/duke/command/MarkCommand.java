package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;
import duke.task.TaskList;

/**
 * A command to mark tasks as completed.
 */
public class MarkCommand {
    /**
     * Marks a specified task from a list of tasks as complete.
     * @param tasks the list of tasks.
     * @param input user's input containing the task to be marked.
     * @return acknowledgement of the successful marking of the task.
     * @throws DukeException if the task number were found to be invalid.
     */
    public static String executeMark(TaskList tasks, String input) throws DukeException {
        if (input.length() < 6 || !input.matches("mark\\s+\\d+")) {
            throw new DukeException("<mark> command should receive a task number!");
        }
        int taskNum = Integer.parseInt(input.substring(5).trim());
        if (1 <= taskNum && taskNum <= tasks.getSize()) {
            String marked = tasks.toggleMark(taskNum);
            return "Nice! I've marked this task as done:\n\t" + marked;
        } else {
            throw new InvalidIndexException(taskNum);
        }
    }
}
