package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command to delete an existing task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the intended task from tasks and
     * updates the storage file accordingly.
     * Informs user of successful execution of command via the ui.
     * @param tasks
     * @param ui
     * @param storage
     * @throws IOException
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex >= tasks.size()) {
            String errorMessage = String.format("Task %d does not exist!", taskIndex + 1);
            throw new DukeException(errorMessage);
        } else {
            Task task = tasks.get(this.taskIndex);
            ui.showMessage("Ok, I've deleted the following task for you:");
            ui.showMessage(task.toString());
            tasks.remove(this.taskIndex);
            String msg = String.format("You now have %d task(s) in your list!", tasks.size());
            ui.showMessage(msg);
            storage.saveTasks(tasks.getTasks());
        }
    }
}
