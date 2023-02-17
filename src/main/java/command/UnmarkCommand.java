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
    private static int NUM_COMPONENTS = 2;

    /**
     * Default constructor
     * @param command the command
     * @param doesPrint whether to print messages
     * @throws DukeException when the input command cannot be parsed
     */
    public UnmarkCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
    }

    /**
     * Unmarks a task
     * @param taskList the list of tasks
     * @return the string representation of the reply
     * @throws DukeException when the index or command format is incorrect
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            int idx = Integer.parseInt(command.substring("unmark".length() + 1)) - 1;
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
