package command;

import task.TaskManager;
import task.ToDo;
import util.DukeException;
import util.DukeUI;

/**
 * Executes add todo task command.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Executes command to add a todo task to the list.
     * @param description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo type task to the list.
     * @param taskManager
     * @return Successful add todo message
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        ToDo todo = new ToDo(this.description, false);
        taskManager.addTaskToList(todo);
        return DukeUI.todoTaskMessage(todo, taskManager.getTaskArraySize());
    }
}
