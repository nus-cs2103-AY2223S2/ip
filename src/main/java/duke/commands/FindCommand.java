package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
