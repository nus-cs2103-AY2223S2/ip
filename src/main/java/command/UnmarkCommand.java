package command;

import duke.DukeException;
import duke.NumberFormatDukeException;
import duke.IndexOutOfBoundsDukeException;
import task.Task;
import task.TaskList;
import ui.TextUi;

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
     * Execute the task
     * @param taskList the list of tasks
     * @param ui       a text UI
     */
    @Override
    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        try {
            int idx = Integer.parseInt(command.substring(7)) - 1;
            Task task = taskList.get(idx);
            task.unmarkDone();
            uiPrint(ui, String.format("OK, I've marked this task as not done yet:\n  %s", task));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException();
        } catch (NumberFormatException e) {
            throw new NumberFormatDukeException();
        }
    }

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
