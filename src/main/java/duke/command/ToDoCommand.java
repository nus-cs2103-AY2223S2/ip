package duke.command;

import duke.dukeexception.CommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;

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
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            String description = values[0];

            Task task = new Todo(description);
            tasks.add(task);
            storage.save(tasks);

            return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (CommandException error) {
            return error.getMessage();
        }
    }
}
