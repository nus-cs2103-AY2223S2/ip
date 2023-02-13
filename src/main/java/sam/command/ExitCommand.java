package sam.command;

import sam.storage.Storage;
import sam.task.TaskList;
import sam.ui.Dialog;
import sam.ui.Ui;

/**
 * Represents a user command to exit from Sam.
 */
public class ExitCommand extends Command {
    public ExitCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond(Dialog.BYE.getDialog());
    }
}
