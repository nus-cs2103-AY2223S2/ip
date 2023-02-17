package commands;

import files.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Command which stops Duke from accepting anymore commands so that it can exit the program,
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the procedure of stopping Duke from accepting anymore commands.
     * @param taskList task list to do task operations on
     * @param ui user interface to display exit message
     * @param storage storage for reading and writing data to files
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayByeMessage();
        return ui.getByeMsg();
    }
}
