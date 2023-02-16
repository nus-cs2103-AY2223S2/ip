package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;

/**
 * Deals with execution of 'list'
 */
public class ListCommand extends Command {
    public ListCommand() {}

    /**
     * Executes the required actions for 'list' and generates its corresponding response.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList.getTasks());
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
