package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markTaskDone(index);
        storage.save(tasks.createTaskListString());
        return ui.showMarkTask(task);
    }

}
