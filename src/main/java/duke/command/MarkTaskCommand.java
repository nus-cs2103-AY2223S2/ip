package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Mark command.
 */
public class MarkTaskCommand extends Command {
    private int idx;
    public MarkTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(idx - 1);
        storage.save(tasks.getList());
        return ui.formResponse("Task marked as done: " + tasks.getTask(idx - 1));
    }

}
