package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super("MARK");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.setDone(this.index);
    }
}
