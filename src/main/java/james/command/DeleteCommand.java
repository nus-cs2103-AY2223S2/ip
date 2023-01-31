package james.command;

import james.JamesException;
import james.task.Task;

/**
 * The command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
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
