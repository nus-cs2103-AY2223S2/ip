package command;

import duke.DukeException;
import duke.IndexOutOfBoundsDukeException;
import duke.NumberFormatDukeException;
import task.Task;
import task.TaskList;

/**
 * Mark a task as undone
 */
public class UnmarkCommand extends Command {
    /**
     * Default constructor
     * @param command the command
     * @param doesPrint whether to print messages
     */
    public UnmarkCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 2);
    }

    /**
     * Unmarks a task
     * @param taskList the list of tasks
     * @return the string reprepsentation of the reply
     * @throws DukeException when the index or command format is incorrect
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            int idx = Integer.parseInt(command.substring(7)) - 1;
            Task task = taskList.get(idx);
            task.unmarkDone();
            return String.format("OK, I've marked this task as not done yet:\n  %s", task);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException();
        } catch (NumberFormatException e) {
            throw new NumberFormatDukeException();
        }
    }
}
