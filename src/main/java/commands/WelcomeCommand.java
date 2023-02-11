package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class WelcomeCommand implements Command {

    /**
     * Prints the welcome message.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.welcomeMessage();
    }
}
