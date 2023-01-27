package duke.command;

import duke.exception.EmptyDescException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    private Todo todo;
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
            Task t = taskList.addTasks(todo);
            storage.updateStorage();
            ui.printAddTask(taskList, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
