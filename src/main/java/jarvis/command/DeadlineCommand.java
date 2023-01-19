package jarvis.command;

import jarvis.Storage;
import jarvis.task.TaskList;
import jarvis.Ui;
import jarvis.exception.CommandParseException;
import jarvis.task.DeadlineTask;

import java.util.List;


/**
 * Command class to delete deadline tasks.
 */
public class DeadlineCommand extends Command {
    private final String deadline;

    public DeadlineCommand(Action action, String body, List<Command> subCommands) {
        super(action, body, subCommands);
        String deadline = null;
        for (Command command : subCommands) {
            if (command.hasAction(Action.DEADLINE_BY)) {
                deadline = command.getBody();
                break;
            }
        }
        this.deadline = deadline;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            ui.print(taskList.addTask(new DeadlineTask(this.getBody(), this.deadline)));
        } catch (CommandParseException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}
