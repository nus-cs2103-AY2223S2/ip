package command;

import task.TaskManager;
import task.ToDo;
import util.DukeException;

public class TodoCommand extends Command {
    private final TaskManager taskManager;
    private final String description;

    public TodoCommand(TaskManager taskManager, String description) {
        this.taskManager = taskManager;
        this.description = description;
    }

    @Override
    public void executeCommand() throws DukeException {
        ToDo todo = new ToDo(this.description);
        taskManager.addTaskToList(todo);
    }
}
