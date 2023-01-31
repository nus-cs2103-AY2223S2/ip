package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * A command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {

    private final int num;
    DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage Storage to save and load list of tasks.
     * @throws DukeException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.delete(num - 1);
            ui.showBunny();
            ui.delete(t, tasks);
        }
        storage.saveTasks(tasks);
    }

}