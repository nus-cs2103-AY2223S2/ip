package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Mark command to handle task marking logic.
 */
public class MarkCommand extends Command {
    private int index;
    public MarkCommand(String message) {
        index = Integer.parseInt(message) - 1;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        String oldText = tl.toText(this.index);
        tl.markTask(this.index);
        String newText = tl.toText(this.index);
        s.modifyTask(oldText, newText);
        ui.display("Nice! I've marked this task as done:" + LS + tl.toString(this.index));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
