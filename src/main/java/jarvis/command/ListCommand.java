package jarvis.command;

import java.util.List;

import jarvis.exception.command.InvalidParameterException;
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

        Command fromCommand = this.getSubCommand(Action.EVENT_FROM);
        Command toCommand = this.getSubCommand(Action.DEADLINE_BY, Action.EVENT_TO);
        String afterDate = Command.hasBody(fromCommand)
                ? fromCommand.getBody()
                : null;
        String beforeDate = Command.hasBody(toCommand)
                ? toCommand.getBody()
                : null;

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
