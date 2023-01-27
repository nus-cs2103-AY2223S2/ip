package command;

import duke.DukeException;
import task.Task;
import task.TaskList;
import ui.TextUi;

/**
 * Command for deleting a task
 */
public class DeleteTaskCommand extends TaskCommand {

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messages
     */
    public DeleteTaskCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 2);
    }

    /**
     * Execute the task
     * @param taskList the list of tasks
     * @param ui       a text UI
     * @throws DukeException
     */
    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        String indexToDelete = getCommandContent(command);
        int idx = Integer.parseInt(indexToDelete);
        idx = idx - 1; // count from zero
        Task t = taskList.get(idx);
        taskList.remove(idx);
        String toPrint = String.format("Noted. I've removed this task:\n  %s\n"
                + "Now you have %d tasks in the list.", t, taskList.size());
        uiPrint(ui, toPrint);
    }
}
