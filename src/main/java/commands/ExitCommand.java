package commands;

import files.Storage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayByeMessage();
    }
}
