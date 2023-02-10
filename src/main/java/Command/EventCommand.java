package Command;

import Task.Task;
import Task.TaskList;

/**
 * Command class for event
 */
public class EventCommand implements Command {
    private Task newEvent;
    public EventCommand(Task t) {
        newEvent = t;
    }
    @Override
    public String execute(TaskList taskList) {
        return taskList.add(newEvent);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
