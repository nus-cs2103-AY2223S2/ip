package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public void execute(TaskList list, Ui ui, Storage storage) {

    }

    public boolean isExit() {
        return true;
    }

}
