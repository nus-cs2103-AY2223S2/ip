package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showError("Invalid command! Use command 'help' to see the commands available for use :)");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
