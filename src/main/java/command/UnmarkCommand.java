package command;

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
    public UnmarkCommand(String command, boolean doesPrint) {
        super(command, doesPrint);
    }

    /**
     * Execute the task
     * @param taskList the list of tasks
     * @param ui       a text UI
     */
    @Override
    public void execute(TaskList taskList, TextUi ui) {
        int idx = Integer.parseInt(command.substring(7)) - 1;
        Task t = taskList.get(idx);
        t.unmarkDone();
        uiPrint(ui, String.format("OK, I've marked this task as not done yet:\n  %s", t));
    }
}
