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

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public MarkCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 2);
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
