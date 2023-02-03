package jarvis.command;

import java.util.List;

import jarvis.exception.command.CommandParseException;
import jarvis.storage.Storage;
import jarvis.task.EventTask;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Command class to create event tasks.
 */
public class EventCommand extends Command {

    public EventCommand(String body, List<Command> subCommands) {
        super(Action.CREATE_EVENT, body, subCommands);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Command fromCommand = this.getSubCommand(Action.EVENT_FROM);
        Command toCommand = this.getSubCommand(Action.EVENT_TO);
        String fromDate = Command.hasBody(fromCommand)
                ? fromCommand.getBody()
                : null;
        String toDate = Command.hasBody(toCommand)
                ? toCommand.getBody()
                : null;

        try {
            ui.print(taskList.addTask(new EventTask(this.getBody(), fromDate, toDate)));
        } catch (CommandParseException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}
