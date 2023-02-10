package Command;

import Task.TaskList;

/**
 * Command class for listing out
 */
public class ListCommand implements Command {
    public ListCommand() {}
    @Override
    public String execute(TaskList taskList) {
        return taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
