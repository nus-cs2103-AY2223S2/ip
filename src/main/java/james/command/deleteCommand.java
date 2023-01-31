package james.command;

import james.JamesException;
import james.task.Task;

public class deleteCommand extends Command {
    private int index;

    public deleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof deleteCommand) {
            return index == ((deleteCommand) obj).index;
        }
        return false;
    }

    @Override
    public void execute() throws JamesException {
        if (index >= taskList.getSize()) {
            throw new JamesException("Task number is out of range!");
        }
        Task removedTask = taskList.deleteTask(index);
        ui.eraseTask(removedTask, taskList.getSize());
    }

}
