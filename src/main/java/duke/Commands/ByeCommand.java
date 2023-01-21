package duke.Commands;

import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

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
