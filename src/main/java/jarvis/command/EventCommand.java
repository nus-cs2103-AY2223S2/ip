package jarvis.command;

import jarvis.Storage;
import jarvis.task.TaskList;
import jarvis.Ui;
import jarvis.exception.CommandParseException;
import jarvis.task.EventTask;

import java.util.List;

public class EventCommand extends Command {
    private final String fromDate;
    private final String toDate;

    public EventCommand(Action action, String body, List<Command> subCommands) {
        super(action, body, subCommands);
        String fromDate = null;
        String toDate = null;
        for (Command command : subCommands) {
            if (command.hasAction(Action.EVENT_FROM) && fromDate == null) {
                fromDate = command.getBody();
            } else if (command.hasAction(Action.EVENT_TO) && toDate == null) {
                toDate = command.getBody();
            }
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            ui.print(taskList.addTask(new EventTask(this.getBody(), this.fromDate, this.toDate)));
        } catch (CommandParseException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}
