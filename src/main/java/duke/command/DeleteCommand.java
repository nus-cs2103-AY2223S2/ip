package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(this.index);
        ui.showDeleteTask(task, tasks);
        storage.save(tasks.createTaskListString());
    }
}
