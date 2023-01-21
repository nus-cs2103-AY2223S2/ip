package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
