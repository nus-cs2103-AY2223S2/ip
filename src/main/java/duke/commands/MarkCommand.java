package duke.commands;
import duke.ui.Ui;
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
    public String execute(TaskList tasks) {
        return tasks.setDone(this.index);
    }
}
