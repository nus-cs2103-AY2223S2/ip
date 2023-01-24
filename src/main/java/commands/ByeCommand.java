package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ui.display("Bye. Hope to see you again soon!");
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
