package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.InvalidParameterException;
import jarvis.task.TaskFilter;

import java.util.List;


/**
 * Command class to list and filter tasks.
 */
public class ListCommand extends Command {
    private TaskFilter filter;

    public ListCommand(Action action, String body, List<Command> subCommands) {
        super(action, body, subCommands);

        String afterDate = null;
        String beforeDate = null;
        for (Command command : subCommands) {
            if (command.hasAction(Action.DEADLINE_BY, Action.EVENT_TO) && beforeDate == null) {
                beforeDate = command.getBody();
            } else if (command.hasAction(Action.EVENT_FROM) && afterDate == null) {
                afterDate = command.getBody();
            }
        }
        try {
            this.filter = new TaskFilter()
                    .setAfterDate(afterDate)
                    .setBeforeDate(beforeDate)
                    .addKeywords(body);
        } catch (InvalidParameterException ignored) {}
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.print(taskList.getTasksForPrint(this.filter));
    }
}
