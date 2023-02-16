package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;
import genie.task.Task;

/**
 * Deals with execution of 'unmark'
 */
public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the required actions for 'unmark' and generates its corresponding response.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task t = tasklist.getTasks().get(index - 1);
        t.unmarkDone();
        ui.appendUnmarkDoneMessage(t);
    }

    /**
     * Checks if command is 'bye'
     * @return true if is 'bye', false otherwise
     */
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
