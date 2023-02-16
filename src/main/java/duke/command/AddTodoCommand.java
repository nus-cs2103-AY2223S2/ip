package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.textui.TextUi;

/**
 * A command that stores the command to add a new todo task. The action of adding the task can be carried out when
 * called.
 */
public class AddTodoCommand extends Command {
    /**
     * The DESCRIPTION of the todo task.
     */
    private final String data;

    /**
     * Constructor for a command to add new todo task.
     *
     * @param data The DESCRIPTION of the todo task
     */
    public AddTodoCommand(String data) {
        super(AvailableCommands.ADD_TODO);
        this.data = data;
    }

    /**
     * Adds a new todo task into the task list with the given DESCRIPTION.
     *
     * @param taskList List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     * @return The string of what is printed out after execution
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        Task todo = new Todo(data);
        taskList.addTask(todo);

        return ui.showAddTask(todo.toString(), taskList.size());
    }
}
