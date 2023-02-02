package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Class of DeleteCommand that remove tasks.
 */
public class DeleteCommand extends Command {
    private int id;

    public DeleteCommand(String cmd) {
        this.id = Integer.parseInt(cmd);
    }

    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        Task task = tl.removeTask(this.id - 1);
        System.out.println("Noted. I've removed this duke.task:\n" + task
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
