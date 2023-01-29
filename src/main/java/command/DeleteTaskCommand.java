package command;

import duke.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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

