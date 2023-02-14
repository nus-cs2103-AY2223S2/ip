package duke.commands;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        super("UNMARK");
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.setNotDone(this.index);
    }
}
