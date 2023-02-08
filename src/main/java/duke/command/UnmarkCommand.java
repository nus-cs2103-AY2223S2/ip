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

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = tasks.markTaskUndone(index);
        storage.save(tasks);
        return ui.showUnmarkTask(unmarkedTask);
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
        return "Command: Mark task " + index;
    }
}
