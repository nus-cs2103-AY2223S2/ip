package james.command;

import james.JamesException;
import james.task.TaskList;
import james.UI;

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
