package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showDeleteTask(deletedTask, tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Command: Delete task " + index;
    }
}
