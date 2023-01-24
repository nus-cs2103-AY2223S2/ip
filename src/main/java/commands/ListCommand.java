package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static ui.Ui.LS;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ui.display("Here are the tasks in your list:" + LS + tl.formatList());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
