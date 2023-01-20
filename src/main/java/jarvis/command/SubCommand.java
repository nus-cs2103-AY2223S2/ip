package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

import java.util.List;

/**
 * Command class representing secondary commands (of form '/command ...').
 */
public class SubCommand extends Command {
    public SubCommand(Action action, String body, List<Command> subCommands) {
        super(action, body, subCommands);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {}
}
