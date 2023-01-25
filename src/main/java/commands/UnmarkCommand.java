package commands;

import static ui.Ui.LS;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(String message) {
        index = Integer.parseInt(message) - 1;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        String oldText = tl.toText(this.index);
        tl.unmarkTask(this.index);
        String newText = tl.toText(this.index);
        s.modifyTask(oldText, newText);
        ui.display("OK, I've marked this task as not done yet:" + LS + tl.toString(this.index));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
