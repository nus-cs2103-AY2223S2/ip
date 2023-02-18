package duke.commands;

import duke.enums.CommandType;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Incorrect command to handle incorrect instructions.
 */
public class IncorrectCommand extends Command {
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ui.showError(CommandType.INCORRECT.getErr().getMessage());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
