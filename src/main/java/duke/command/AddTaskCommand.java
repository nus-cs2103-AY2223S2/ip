package duke.command;


import duke.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

public class AddTaskCommand extends Command {

    protected Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        list.add(task);
        ui.addTask(task, list.getLength());
        storage.write(list);
    }

    public boolean isExit() {
        return false;
    }

}
