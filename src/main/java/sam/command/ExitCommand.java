package sam.command;

import sam.storage.Storage;
import sam.task.TaskList;
import sam.ui.Dialog;

/**
 * Represents a user command to exit from Sam.
 */
public class ExitCommand extends Command {
    public ExitCommand(String args) {
        super(args);
    }

    @Override
    public Result execute(TaskList tasks, Storage storage) {
        result.addMessage(Dialog.BYE.getDialog());
        return result;
    }
}
