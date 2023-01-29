package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;


/**
 * Class of UnmarkCommand that set task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int id;

    public UnmarkCommand(String cmd) {
        this.id = Integer.parseInt(cmd);
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = tl.getTask(this.id - 1);
        t.setUnmark();
        System.out.println("OK, I've marked this duke.task as not done yet:\n" + t);
        return true;
    }
}
