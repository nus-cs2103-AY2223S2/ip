package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;

import java.io.IOException;

/**
 * Deals with execution of 'bye'
 */
public class ExitCommand extends Command {
    public ExitCommand() {}

    /**
     * Executes the required actions for 'bye' and generates its corresponding response.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showFarewellMessage();
        storage.saveListToFile(taskList.getTasks());
        storage.closeFileWriter();
    }

    /**
     * Checks if command is 'bye'
     * @return true if is 'bye', false otherwise
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }
}
