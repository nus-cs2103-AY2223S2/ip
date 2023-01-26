package jarvis.command;

import java.util.List;

import jarvis.exception.InvalidParameterException;
import jarvis.storage.Storage;
import jarvis.task.TaskFilter;
import jarvis.task.TaskList;
import jarvis.ui.Ui;


/**
 * Command class to list and filter tasks.
 */
public class ListCommand extends Command {
    private TaskFilter filter;

    /**
     * Constructor for a list command.
     *
     * @param body Keywords to search for.
     * @param subCommands Supplementary commands.
     */
    public ListCommand(String body, List<Command> subCommands) {
        super(Action.LIST, body, subCommands);

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
        } catch (InvalidParameterException e) {
            this.filter = new TaskFilter();
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.print(taskList.getTasksForPrint(this.filter));
    }
}
