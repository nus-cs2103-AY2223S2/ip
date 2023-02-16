package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.invalidCmdMsg();
    }
}
