package command;

import duke.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkTaskCommand extends Command {

    private int idx;
    public UnmarkTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmarkTask(idx - 1);
        ui.formResponse("Task marked as undone: " + tasks.getTask(idx - 1));
        storage.save(tasks.getList());
    }
}
