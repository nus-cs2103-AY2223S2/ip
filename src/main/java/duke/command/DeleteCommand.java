package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskStore, Ui ui, Storage storage) throws DukeException {
        Task task = taskStore.deleteTask(this.index);
        ui.showDeleteTask(task, taskStore);
        storage.save(taskStore.createTaskListString());
    }
}
