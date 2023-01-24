package command;

import task.TaskManager;
import task.ToDo;

public class TodoCommand extends Command {
    private final TaskManager taskManager;
    private final String description;

    public TodoCommand(TaskManager taskManager, String description) {
        this.taskManager = taskManager;
        this.description = description;
    }

    @Override
    public void executeCommand() {
        ToDo todo = new ToDo(this.description);
        taskManager.addTaskToList(todo);
    }
}
