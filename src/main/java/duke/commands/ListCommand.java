package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
