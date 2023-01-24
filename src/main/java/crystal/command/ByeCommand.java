package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui,Storage storage) {
        ui.printBye();
    }

    public boolean isExit() {
        return true;
    }
}
