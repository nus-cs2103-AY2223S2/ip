package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.exit();
        ui.endChat();
    }
}
