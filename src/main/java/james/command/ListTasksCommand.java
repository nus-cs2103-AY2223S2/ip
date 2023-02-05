package james.command;

import james.JamesException;
import james.task.TaskList;
import james.ui.Ui;

/**
 * The command to list all tasks in the task list.
 */
public class ListTasksCommand extends Command {
    public ListTasksCommand() {
    }

    public void assign(TaskList taskList, Ui ui) {
        super.assign(taskList, ui);
    }

    @Override
    public void execute() throws JamesException {
        ui.printTaskList(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListTasksCommand;
    }


}
