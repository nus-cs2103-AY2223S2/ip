package chattime.command;

import chattime.storage.Storage;
import chattime.task.TaskList;
import chattime.ui.Ui;

public class ByeCommand extends Command {


    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.exit();
        ui.endChat();
    }
}
