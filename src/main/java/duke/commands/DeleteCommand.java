package duke.commands;
import duke.dukeexceptions.InvalidArgumentException;
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
    public String execute(TaskList tasks) throws IndexOutOfBoundsException {
        assert this.index >= 0;
        try {
            return tasks.delete(index);
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidArgumentException("Index " + (this.index + 1) + " is out of bound.");
        }
    }
}
