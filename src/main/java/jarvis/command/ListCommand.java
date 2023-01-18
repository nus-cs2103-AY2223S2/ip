package jarvis.command;

import jarvis.Storage;
import jarvis.task.TaskList;
import jarvis.Ui;
import jarvis.exception.InvalidParameterException;
import jarvis.task.TaskFilter;

import java.util.List;

public class ListCommand extends Command {
    private final TaskFilter filter;

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
        TaskFilter filter = null;
        try {
            filter = new TaskFilter(afterDate, beforeDate);
        } catch (InvalidParameterException ignored) {}
        this.filter = filter;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.print(taskList.getTasksForPrint(this.filter));
    }
}
