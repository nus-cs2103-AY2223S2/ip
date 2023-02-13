package jarvis.command.subcommand;

import jarvis.command.Command;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Command class representing secondary commands (of format '/command ...').
 */
public abstract class SubCommand extends Command {
    public SubCommand(Action action, String body) {
        super(action, body);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        // Subcommands do nothing when executed, as they only carry data.
    }
}
