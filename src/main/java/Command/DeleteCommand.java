package Command;

import Task.TaskList;

/**
 * Command class for delete
 */
public class DeleteCommand implements Command {
    private int deleteIndex;
    public DeleteCommand(int i) {
        deleteIndex = i;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.delete(deleteIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
