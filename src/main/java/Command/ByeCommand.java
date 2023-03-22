package Command;

import Task.TaskList;

/**
 * This command doesn't do anything to task list
 * Instead, it set exit as true and will shut down process in duke class
 */
public class ByeCommand implements Command {
    public ByeCommand() {}

    @Override
    public String execute(TaskList taskList) {
        return taskList.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
