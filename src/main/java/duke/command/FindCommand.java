package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Find is a command that finds tasks in the tasklist filtered by a specific keyword.
 */
public class FindCommand extends Command {
    private String taskname;

    /**
     * Constructor for FindCommand.
     * @param taskname Keyword used to filter tasks in the tasklist.
     */
    public FindCommand(String taskname) {
        super(false);
        this.taskname = taskname;
    }
    /**
     * Finds a specific task in the tasklist using a keyword.
     * @param task Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @throws DukeException if command cannot be recognised.
     */
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        ui.printFindList(task.findTask(taskname));
    }
}
