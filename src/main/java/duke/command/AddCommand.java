package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks.createTaskListString());
        return ui.showAddTask(task, tasks);
    }

}
