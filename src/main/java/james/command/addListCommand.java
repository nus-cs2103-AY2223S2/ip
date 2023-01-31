package james.command;

import james.JamesException;
import james.task.TaskList;
import james.ui.UI;

/**
 * The command to list all tasks in the task list.
 */
public class addListCommand extends Command {
    public addListCommand() {
    }

    public void assign(TaskList taskList, UI ui) {
        super.assign(taskList, ui);
    }

    @Override
    public void execute() throws JamesException {
        ui.printTaskList(taskList);
    }

}
