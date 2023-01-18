package jarvis.command;

import jarvis.Storage;
import jarvis.TaskList;
import jarvis.Ui;

import java.util.List;

public class SubCommand extends Command {
    public SubCommand(Action action, String body, List<Command> subCommands) {
        super(action, body, subCommands);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {}
}
