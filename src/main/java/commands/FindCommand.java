package commands;

import static ui.Ui.LS;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Find command to handle task finding logic.
 */
public class FindCommand extends Command {
    private String s;
    public FindCommand(String s) {
        this.s = s;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ui.display("Here are the matching tasks in your list:" + LS + tl.findList(this.s));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
