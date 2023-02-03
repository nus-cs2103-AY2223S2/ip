package duke.commands;
import duke.Ui;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.setNotDone(this.index);
    }
}
