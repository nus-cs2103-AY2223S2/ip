package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Todo;

/**
 * The TodoCommand class encapsulates the variables and methods related to Todo commands.
 */
public class ToDoCommand extends Command {
    public static final String TODO_COMMAND = "todo";
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
    public String execute(TaskList lst, Ui ui) throws DukeException {
        lst.addTask(this.todo);
        return ui.showTodo(this.todo, lst.getSize());
    }
}
