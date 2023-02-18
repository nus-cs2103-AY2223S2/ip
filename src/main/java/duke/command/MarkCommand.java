package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class MarkCommand extends Command {
    int index;

    /**
     * Class constructor.
     *
     * @param index index of item to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done and returns a "taskMarked" message.
     * Returns an error if task was already marked.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return "taskMarked" message.
     * @throws DukeException if task was already marked.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            if (taskList.getTask(index).isDone()) {
                throw new DukeException("This task has already been marked as done.");
            } else {
                taskList.getTask(index).mark();
                return ui.printMark(taskList.getTask(index));
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
