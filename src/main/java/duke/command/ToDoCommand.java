package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Todo;

/**
 * The TodoCommand class encapsulates the variables and methods related to Todo commands.
 */
public class ToDoCommand extends Command {
    private static final String TODO_COMMAND = "todo";
    private final Todo todo;

    /**
     * Constructor creates an instance of TodoCommand.
     * @param todo An instance of Todo.
     */
    public ToDoCommand(Todo todo) {
        super(TODO_COMMAND);
        this.todo = todo;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.addTask(this.todo);
        return ui.showTodo(this.todo, taskList.getSize());
    }
}
