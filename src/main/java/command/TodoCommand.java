package command;

import task.TaskManager;
import task.ToDo;

import util.DukeException;

/**
 * Executes add todo task command.
 */
public class TodoCommand extends Command {
    //private final TaskManager taskManager;
    private final String description;

    public TodoCommand(String description) {
        //this.taskManager = taskManager;
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a todo type task to the list.
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskManager taskManager) throws DukeException {
        ToDo todo = new ToDo(this.description, false);
        taskManager.addTaskToList(todo);
    }
}
