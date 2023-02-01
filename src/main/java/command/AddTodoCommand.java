package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;
import task.Todo;

/**
 * Adds todo tasks.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs AddTodoCommand.
     *
     * @param description Description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task to the list of task, outputs message to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if user input is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = new Todo(description);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputAddTask(t);
    }
}

