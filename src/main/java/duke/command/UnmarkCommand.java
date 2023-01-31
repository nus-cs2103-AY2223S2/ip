package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand is a command that UnMarks a task in the taskList.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index Index of specific task in the taskList to be unmark.
     */
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * UnMarks the specific task in the taskList and show message to user.
     *
     * @param task TaskList containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if duke does not recognise the command.
     */
    @Override
    public String execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.unmarkTask(index);
        storage.saveTasksToFile(task.getListOfTasks());
        return ui.showUnmark(task.getTask(index));
    }
}
