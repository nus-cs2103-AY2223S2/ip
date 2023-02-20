package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Adds a Todo type task.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Main constructor
     *
     * @param description description of the task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a Todo type task.
     * Ask UI to print the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getAllTasks());
        return Ui.showAddMessage(todo, tasks.size());
    }
}