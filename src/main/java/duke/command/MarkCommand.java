package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;


/**
 * Class of MarkCommand that set task as completed.
 */
public class MarkCommand extends Command {
    private int id;

    public MarkCommand(String cmd) {
        this.id = Integer.parseInt(cmd);
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = tl.getTask(this.id - 1);
        t.setMark();
        System.out.println("Nice! I've marked this duke.task as done:\n" + t);
        return true;
    }
}
