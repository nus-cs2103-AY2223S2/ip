package jarvis.command;

import java.util.List;

import jarvis.exception.command.CommandParseException;
import jarvis.storage.Storage;
import jarvis.task.DeadlineTask;
import jarvis.task.TaskList;
import jarvis.ui.Ui;


/**
 * Command class to create deadline tasks.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String body, List<Command> subCommands) {
        super(Action.CREATE_DEADLINE, body, subCommands);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Command byCommand = this.getSubCommand(Action.DEADLINE_BY);
        if (!Command.hasBody(byCommand)) {
            ui.printError("The deadline ('/by ...') is missing.");
            return;
        }

        try {
            ui.print(taskList.addTask(new DeadlineTask(this.getBody(), byCommand.getBody())));
        } catch (CommandParseException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}
