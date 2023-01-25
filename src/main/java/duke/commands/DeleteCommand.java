package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The duke.commands.DeleteCommand class implements the action of deleting tasks.
 *
 * @author Chia Jeremy
 */

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.getTask(index);
        tasks.delete(index);
        storage.delete(index);
        ui.display("Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
