package Commands;

import Files.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayByeMessage();
    }
}
