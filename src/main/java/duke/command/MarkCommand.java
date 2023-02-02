package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Class of MarkCommand that set task as completed.
 */
public class MarkCommand extends Command {
    private int id;

    public MarkCommand(String cmd) {
        this.id = Integer.parseInt(cmd);
    }

    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        Task task = tl.getTask(this.id - 1);
        task.setMark();
        System.out.println("Nice! I've marked this duke.task as done:\n" + task);
        return true;
    }
}
