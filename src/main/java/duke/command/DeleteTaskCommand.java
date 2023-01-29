package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    private int index;
    public DeleteTaskCommand(int taskIndex) {
        this.index = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deleted = tasks.deleteTask(index - 1);
        ui.formResponse("Task deleted: " + deleted);
        storage.save(tasks.getList());
    }
}

