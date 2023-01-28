package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
import crystal.task.Todo;
public class TodoCommand extends Command{

    public String task;
    public TodoCommand(String task) {
        this.task = task;
    }
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        Todo todo = new Todo(task);
        tasks.add(todo);
        ui.printTodo(tasks, todo);

    }

    public boolean isExit() {
        return false;
    }
}
