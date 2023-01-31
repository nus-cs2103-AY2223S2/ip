package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    public boolean isExit() {
        return true;
    }
}
