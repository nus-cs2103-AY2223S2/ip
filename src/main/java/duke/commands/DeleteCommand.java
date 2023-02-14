package duke.commands;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super("DELETE");
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.delete(index);
    }
}
