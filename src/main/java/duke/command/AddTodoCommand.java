package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * A command that stores the command to add a new todo task. The action of adding the task can be carried out when
 * called.
 */
public class AddTodoCommand extends Command {
    /**
     * The description of the todo task.
     */
    private final String DATA;

    /**
     * Constructor for a command to add new todo task.
     *
     * @param commandString The add todo command in string representation
     * @param DATA The description of the todo task
     */
    public AddTodoCommand(String commandString, String DATA) {
        super(Commands.ADD_TODO, commandString);
        this.DATA = DATA;
    }

    /**
     * Adds a new todo task into the task list with the given description.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(this.DATA);
        tasks.addTask(todo);

        ui.showAddTask(todo.toString(), tasks.size());
    }
}
