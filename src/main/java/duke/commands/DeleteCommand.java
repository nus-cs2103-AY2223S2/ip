package duke.commands;
import duke.Ui;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(index);
    }
}
