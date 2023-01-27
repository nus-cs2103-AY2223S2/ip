package command;

import duke.Duke;
import duke.DukeException;
import task.Task;
import task.TaskList;
import ui.TextUi;

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
        super(command, doesPrint, 3);
    }

    /**
     * Execute the task
     * @param taskList the list of tasks
     * @param ui       a text UI
     */
    @Override
    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        int idx = Integer.parseInt(command.split(" ")[1]);  //TODO: debug loading wrong commands. handle exception.
        idx = idx - 1;
        try {
            Task t = taskList.get(idx);
            t.markDone();
            uiPrint(ui, String.format("Nice! I've marked this task as done:\n  %s", t));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.toString());
        }
    }
}
