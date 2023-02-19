package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public String execute(TaskList list, Ui ui) {
        return ui.printByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
