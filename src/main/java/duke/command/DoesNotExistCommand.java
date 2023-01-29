package command;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class DoesNotExistCommand extends Command {

    public DoesNotExistCommand() {
        super("");
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
