package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class UnmarkCommand extends Command {
    int index;

    /**
     * Class constructor.
     *
     * @param index index of item to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as not done and returns a "taskUnmarked" message.
     * Returns an error if task was already marked as not done.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return "taskMarked" message.
     * @throws DukeException if task was already unmarked.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            if (taskList.getTask(index).isDone()) {
                taskList.getTask(index).unmark();
                return ui.printUnmark(taskList.getTask(index));
            } else {
                throw new DukeException("This task was already marked as not done.");
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}