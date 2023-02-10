package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

/**
 * The TodoCommand class encapsulates the variables and methods related to Todo commands.
 */
public class ToDoCommand extends Command {
    public static final String TODO_COMMAND = "todo";
    private final Todo todo;

    public ToDoCommand(Todo todo) {
        super(TODO_COMMAND);
        this.todo = todo;
    }

    @Override
    public void execute(TaskList lst, Ui ui) throws DukeException {
        lst.addTask(this.todo);
        ui.showTodo(this.todo, lst.getSize());
    }
}
