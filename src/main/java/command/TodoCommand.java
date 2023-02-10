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
     * @return
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        ToDo todo = new ToDo(this.description, false);
        assert taskManager != null;
        taskManager.addTaskToList(todo);
        String str = String.format("I have added: %s !", todo);
        String str2 = "There are currently " + taskManager.getTaskArraySize() + " task(s) in the list!";
        return str + System.lineSeparator() + str2;
    }
}
