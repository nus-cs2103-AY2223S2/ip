package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskStore, Ui ui, Storage storage) throws DukeException {
        Task task = taskStore.unmarkTask(index);
        ui.showUnmarkTask(task);
        storage.save(taskStore.createTaskListString());
    }
}
