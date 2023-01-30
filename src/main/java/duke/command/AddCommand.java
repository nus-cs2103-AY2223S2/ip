package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.task.Task;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAddTask(task, tasks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Command: Add task " + task.toString();
    }
}
