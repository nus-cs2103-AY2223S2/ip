package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static ui.Ui.LS;

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
