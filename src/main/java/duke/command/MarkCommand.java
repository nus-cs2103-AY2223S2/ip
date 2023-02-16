package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command that marks a task as completed.
 */
public class MarkCommand extends Command {

    private final int num;
    MarkCommand(int num) {
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String response;
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.mark(num);
            ui.showBunny();
            response = ui.mark(t);
        }
        storage.saveTasks(tasks);
        return response;
    }

}
