package commands;

import static ui.Ui.LS;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * List command to handle task listing logic.
 */
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
