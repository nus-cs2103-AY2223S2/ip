package command;
import main.Storage;
import main.Ui;
import task.Task;
import main.TaskList;

public class TodoCommand implements Command {
    private Task newTask;
    public TodoCommand(Task newTask) {
        this.newTask = newTask;
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        list.add(newTask);
        ui.printAddedTask(newTask);
        storage.save(list);
    }

    public boolean isExit() {
        return false;
    }
}
