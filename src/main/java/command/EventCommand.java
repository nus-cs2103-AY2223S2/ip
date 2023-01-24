package command;

import task.Event;
import task.TaskManager;

public class EventCommand extends Command {
    private final TaskManager taskManager;
    private final String description;
    public EventCommand(TaskManager taskManager, String description) {
        this.taskManager = taskManager;
        this.description = description;
    }
    @Override
    public void executeCommand() {
        String[] arr = this.description.split(" /from ");
        String[] time = arr[1].split(" /to ");
        String start = time[0];
        String end = time[1];
        Event event = new Event(arr[0], start, end);
        taskManager.addTaskToList(event);
    }
}
