package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to delete a task from the list.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute deletion of a task from the taskList base on given index.
     * @param taskList Tasklist containing current tasks.
     * @param ui Ui Component for input and output.
     * @param storage Storage component for persistent storage of Tasks.
     * @return String with details of the deleted task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response;
        try {
            response = taskList.deleteTask(index);
            storage.saveState(taskList);
            return response;
        } catch (DukeException e) {
            response = e.toString();
            return response;
        }
    }
}
