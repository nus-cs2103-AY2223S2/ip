package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to set a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes setting of the task at the given index as incomplete.
     * @param taskList Tasklist containing current tasks.
     * @param ui Ui Component for input and output.
     * @param storage Storage component for persistent storage of Tasks.
     * @return String with details of the unmarked task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response;
        try {
            response = taskList.unmark(index);
            storage.saveState(taskList);
            return response;
        } catch (DukeException e) {
            response = e.toString();
            return response;
        }
    }
}
