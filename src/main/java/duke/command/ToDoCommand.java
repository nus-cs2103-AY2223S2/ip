package command;

import dukeexception.CommandException;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

/**
 * ToDoCommand class extends from Command class.
 */
public class ToDoCommand extends Command {

    /**
     * Constructor.
     * @param request the request from the user
     */
    public ToDoCommand(String request) {
        super(request);
    }

    /**
     * Creates the todo task and store it into the task list.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            String description = values[0];

            Task task = new Todo(description);
            tasks.add(task);

            ui.showTaskAdded(task, tasks.size());

            storage.save(tasks);
        } catch (CommandException error) {
            ui.showError(error);
        }
    }
}
