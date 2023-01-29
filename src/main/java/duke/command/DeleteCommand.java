package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;


/**
 * Class of DeleteCommand that remove tasks.
 */
public class DeleteCommand extends Command {
    private int id;

    public DeleteCommand(String cmd) {
        this.id = Integer.parseInt(cmd);
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t6 = tl.removeTask(this.id - 1);
        System.out.println("Noted. I've removed this duke.task:\n" + t6
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
