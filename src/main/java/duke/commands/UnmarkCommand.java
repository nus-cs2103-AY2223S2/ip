package duke.commands;
import duke.dukeexceptions.InvalidArgumentException;
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
    public String execute(TaskList tasks) throws InvalidArgumentException {
        assert this.index >= 0;
        try {
            return tasks.setNotDone(this.index);
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidArgumentException("Index " + (this.index + 1) + " is out of bound.");
        }
    }
}
