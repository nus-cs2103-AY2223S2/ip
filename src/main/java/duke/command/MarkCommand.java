package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command to mark a task as done
 * or unmark it as not done.
 */
public class MarkCommand extends Command {
    private final boolean isMark;
    private final int taskIndex;

    public MarkCommand(boolean toMarkorUnmark, int taskIndex) {
        this.isMark = toMarkorUnmark;
        this.taskIndex = taskIndex;
    }

    /**
     * Updates the chosen task in tasks to be marked or unmarked
     * and updates the storage file accordingly.
     * Informs user of successful execution of command via the ui.
     * @param tasks TaskList that contains all the current tasks.
     * @param ui Ui that communicates with the user.
     * @param storage Storage that backups the saving of tasks.
     * @throws IOException when storage file cannot be read.
     * @throws DukeException when user input does not comply with intended uses.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex >= tasks.size()) {
            String errorMessage = String.format("Task %d does not exist!", taskIndex + 1);
            throw new DukeException(errorMessage);
        } else {
            Task t = tasks.get(taskIndex);
            if (isMark) {
                t.mark();
                ui.showMessage("Congratulations for completing the task ^^ I've marked it as done:");
            } else {
                t.unmark();
                ui.showMessage("Ok, I've unmarked the task for you:");
            }
            ui.showMessage(t.toString());
            storage.saveTasks(tasks.getTasks());
        }
    }
}
