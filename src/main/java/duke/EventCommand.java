package duke;

/**
 * Command class for event
 */
public class EventCommand implements Command {
    private Task newEvent;
    EventCommand(Task t) {
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
