package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand is a command that unamarks a task in the tasklist.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index Index of specific task in the tasklist to be unmark.
     */
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Unmarks the specifc task in the tasklist and show message to user.
     *
     * @param task Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @throws DukeException if duke does not recognise the command.
     */
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.unmarkTask(index);
        ui.showUnmark(task.getTask(index));
        storage.saveTasksToFile(task.getListOfTasks());
    }
}
