package james.command;

import james.JamesException;
import james.task.TaskList;
import james.UI;

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

}
