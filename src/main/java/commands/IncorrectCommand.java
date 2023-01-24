package commands;

import enums.CommandType;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static ui.Ui.LS;

public class IncorrectCommand extends Command {
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ui.showError(CommandType.INCORRECT.getErr().getMessage());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
