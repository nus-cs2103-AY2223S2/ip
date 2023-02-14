package duke.command;

import java.io.IOException;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNumberToDelete;

    /**
     * Represents a constructor for the DeleteCommand object.
     *
     * @param taskNumberToDelete The task number to be deleted.
     */
    public DeleteCommand(int taskNumberToDelete) {
        this.taskNumberToDelete = taskNumberToDelete;
    }

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the command and deletes a task from the task list.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     * @throws DukeException If there is an error in executing the command.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        if (tl.isEmpty()) {
            throw new DukeException("How to delete an empty list of tasks meowww");
        }
        if (this.taskNumberToDelete > tl.size() || this.taskNumberToDelete < 1) {
            throw new DukeException("Out of range you can delete!");
        }
        Task t = tl.get(taskNumberToDelete - 1);
        tl.deleteTask(taskNumberToDelete - 1);
        String toShow = "Meowww. I've removed this task:\n" + t + "\n" + ui.stringOfTaskNumbers(tl);
        ui.showToUser(toShow);
        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in delete");
        }
        return toShow;
    }
}
