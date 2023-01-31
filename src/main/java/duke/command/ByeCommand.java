package duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printToFormat("Bye, have a nice day.");
    }
}
