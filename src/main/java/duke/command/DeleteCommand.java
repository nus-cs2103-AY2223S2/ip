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
     *
     * @param tasks TaskList that contains all the current tasks
     * @param ui Ui that communicates with the user
     * @param storage Storage that backups the saving of tasks
     * @return string reply to be shown to user after executing this command
     * @throws IOException when storage file cannot be read
     * @throws DukeException when user input does not comply with intended uses
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex >= tasks.size()) {
            String errorMessage = String.format("Task %d does not exist!", taskIndex + 1);
            throw new DukeException(errorMessage);
        }

        Task task = tasks.get(this.taskIndex);
        String msg = "Pew pew! This task is gone now: \n";
        msg += task.toString();
        msg += "\n";
        tasks.remove(this.taskIndex);
        msg += String.format("You now have %d task(s) in your list! \n", tasks.size());
        storage.saveTasks(tasks.getTasks());
        return msg;
    }
}
