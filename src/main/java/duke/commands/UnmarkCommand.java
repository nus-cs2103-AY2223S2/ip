package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmark(this.index);
            int taskNum = index + 1;
            storage.writeToFile(tasks);
            ui.showToUser("You have marked task " + taskNum + " as undone.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist at specified index.");
        } catch (IOException e) {
            throw new DukeException("Unable to write to file. Please run Duke again.");
        }
    }
}
