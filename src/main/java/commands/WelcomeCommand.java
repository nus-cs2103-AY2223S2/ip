package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class WelcomeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.welcomeMessage();
    }
}
