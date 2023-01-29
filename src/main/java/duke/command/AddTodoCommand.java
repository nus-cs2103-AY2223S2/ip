package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    private final String DATA;

    public AddTodoCommand(String commandString, String DATA) {
        super(Commands.ADD_TODO, commandString);
        this.DATA = DATA;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(this.DATA);
        tasks.addTask(todo);

        ui.showAddTask(todo.toString(), tasks.size());
    }
}
