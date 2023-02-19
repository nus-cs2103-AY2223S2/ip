package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to set a task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes setting of the task at the given index as completed.
     * @param taskList Tasklist containing current tasks.
     * @param ui Ui Component for input and output.
     * @param storage Storage component for persistent storage of Tasks.
     * @return String with details of the marked task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response;
        try {
            response = taskList.mark(index);
            storage.saveState(taskList);
            return response;
        } catch (DukeException e) {
            response = e.toString();
            return response;
        }
    }
}
