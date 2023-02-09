package catbot.commands;

import catbot.storage.Storage;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles echoing a message back to the user.
 */
public class EchoCommand extends Command {
    private final String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.setNextOutput(message);
    }
}
