package james.command;

import james.JamesException;
import james.task.TaskList;
import james.ui.UI;

/**
 * The command to list all tasks in the task list.
 */
public class AddListCommand extends Command {
    public AddListCommand() {
    }

    public void assign(TaskList taskList, UI ui) {
        super.assign(taskList, ui);
    }

    @Override
    public void execute() throws JamesException {
        ui.printTaskList(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof addListCommand;
    }


}
