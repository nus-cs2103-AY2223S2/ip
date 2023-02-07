package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command that marks tasks in the TaskList as undone.
 * */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs a UnmarkCommand
     * @param index specifies task to be marked as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Finds the task in TaskList by user inputted index. Calls TaskList method to mark the task as undone.
     * Marking an undone task as undone is allowed and will not result in any error.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param ui Existing Ui used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     * @throws DukeException if something happened to task storage file during runtime or task specified does not exist.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmark(this.index);
            int taskNum = index + 1;
            storage.writeToFile(tasks);
            return ui.showToUser("You have marked task " + taskNum + " as undone.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist at specified index.");
        } catch (IOException e) {
            throw new DukeException("Unable to write to file. Please run Duke again.");
        }
    }
}
