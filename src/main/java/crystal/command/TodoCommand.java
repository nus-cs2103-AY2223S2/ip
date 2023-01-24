package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
import crystal.task.Todo;
public class TodoCommand extends Command{

    public String s;
    public TodoCommand(String s) {
        this.s = s;
    }
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        Todo t = new Todo(s);
        tasks.add(t);
        ui.printTodo(tasks, t);

    }

    public boolean isExit() {
        return false;
    }
}
