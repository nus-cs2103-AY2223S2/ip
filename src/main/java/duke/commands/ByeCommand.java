package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Bye command to exit duke.Duke-Gerty.
 */
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
