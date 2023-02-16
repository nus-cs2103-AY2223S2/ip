package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command that edits a task's description.
 */
public class EditCommand extends Command {

    private final int num;
    private final String newDesc;

    EditCommand(int num, String newDesc) {
        this.num = num;
        this.newDesc = newDesc;
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
        System.out.println(num);
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.edit(num, newDesc);
            ui.showBunny();
            response = ui.edit(t);
        }
        storage.saveTasks(tasks);
        return response;
    }
}
