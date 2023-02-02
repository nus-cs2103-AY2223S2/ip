package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Delete command.
 */
public class CommandDeleteTask extends Command {
    private int index;
    public CommandDeleteTask(int taskIndex) {
        this.index = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deleted = tasks.deleteTask(index - 1);
        ui.formResponse("Task deleted: " + deleted);
        storage.save(tasks.getList());
    }
}

