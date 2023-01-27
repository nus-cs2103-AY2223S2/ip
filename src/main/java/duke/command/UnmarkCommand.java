package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.DukeException;
import duke.task.Task;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = tasks.markTaskUndone(index);
        storage.save(tasks);
        ui.showUnmarkTask(unmarkedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
