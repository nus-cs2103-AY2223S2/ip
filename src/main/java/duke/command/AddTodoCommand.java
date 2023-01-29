package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    private final String DATA;

    public AddTodoCommand(String commandString, String data) {
        super(AvailableCommands.ADD_TODO, commandString);
        DATA = data;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(DATA);
        taskList.addTask(todo);

        ui.showAddTask(todo.toString(), taskList.size());
    }
}
