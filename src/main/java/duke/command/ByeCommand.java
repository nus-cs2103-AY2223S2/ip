package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ByeCommand is a command that ends the session with Duke.
 */
public class ByeCommand extends Command {
    /**
     * Contructor for ByeCommand.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Displays bye message to user.
     * @param task Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @throws DukeException if command cannot be recognised.
     */
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) {
        storage.save(task.getListOfTasks());
        ui.byeMessage();
    }
}
