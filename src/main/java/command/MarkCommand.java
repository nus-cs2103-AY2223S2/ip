package command;

import duke.DukeException;
import duke.IndexOutOfBoundsDukeException;
import duke.NumberFormatDukeException;

import task.Task;
import task.TaskList;

/**
 * Mark a task as done
 */
public class MarkCommand extends Command {
    private static int NUM_COMPONENTS = 2;

    /**
     * Constructor.
     * @param command the user-input command
     * @param doesPrint whether to print response message to console
     * @throws DukeException when exceptions occur
     */
    public MarkCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
    }

    /**
     * Execute the task
     * @param taskList the list of tasks
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            int idx = Integer.parseInt(command.substring(5)) - 1;
            Task t = taskList.get(idx);
            t.markDone();
            return String.format("Nice! I've marked this task as done:\n  %s", t);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException();
        } catch (NumberFormatException e) {
            throw new NumberFormatDukeException();
        }
    }
}
