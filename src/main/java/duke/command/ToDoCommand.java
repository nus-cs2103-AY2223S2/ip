package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * ToDo Command is a command that creates a new todo task.
 */
public class ToDoCommand extends Command {
    String description;

    /**
     * Constructor for ToDoCommand.
     * @param description Description of ToDo task.
     */
    public ToDoCommand(String description) {
        super(false);
        this.description = description;

    }

    /**
     * Adds the todo task into tasklist.
     * @param task Tasklist containing the list of tasks, displays the add message to user.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @throws DukeException if duke does not recognise the command.
     */
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.addToDo(description);
        int size = task.getSize();
        Task temp = task.getTask(size - 1);
        ui.showAdd(temp, size);
    }
}
