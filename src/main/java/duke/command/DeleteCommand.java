package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(this.index);
        storage.save(tasks.createTaskListString());
        return ui.showDeleteTask(task, tasks);
    }

}
