package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command that deletes tasks from TaskList.
 * */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand
     * @param index specifies task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task from TaskList by index.
     * Prompts Ui to display deleted task followed by reminder of number of tasks in TaskList.
     * Prompts Storage to overwrite existing task storage file with new TaskList.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param ui Existing Ui used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     * @throws DukeException if something happened to task storage file during runtime or task specified does not exist.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        try {
            Task deletedTask = tasks.delete(this.index);
            storage.writeToFile(tasks);
            return ui.showToUser("You have deleted: " + deletedTask.toString(),
                    "You have " + tasks.getSize() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist at specified index.");
        } catch (IOException e) {
            throw new DukeException("Unable to write to file. Please run Duke again.");
        }
    }
}
