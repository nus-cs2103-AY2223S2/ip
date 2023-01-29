package duke.command;

import duke.TaskList;
import duke.command.Command;
import duke.storage.Storage;
import duke.ui.Ui;

public class ShowListCommand extends Command {



    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listUI(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
