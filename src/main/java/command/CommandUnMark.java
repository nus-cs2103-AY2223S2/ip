package command;

import duke.DukeException;
import duke.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command to unmark task.
 */
public class CommandUnMark extends Command {

    private final TaskList taskList;
    private final String index;
    private final Storage storage;

    /**
     * Constructor for CommandUnMark
     *
     * @param taskList List of all tasks.
     * @param index Index of task to be unmark, starting from 1.
     */
    public CommandUnMark(TaskList taskList, String index, Storage storage) {
        this.taskList = taskList;
        this.index = index;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        Task unmarkedTask = this.unmarkTaskAt(this.index);
        this.updateFile();
        return this.getConfirmationMessageOf(unmarkedTask);
    }

    private void updateFile() throws DukeException {
        this.storage.overwriteFile(this.taskList);
    }

    private Task unmarkTaskAt(String index) throws DukeException {
        int i = this.getIndex(index);
        return this.taskList.getTaskAt(i).markNotDone();
    }

    private int getIndex(String index) throws DukeException {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private String getConfirmationMessageOf(Task unmarkedTask) {
        return Ui.getUnMarkMessageWithAttitude(unmarkedTask);
    }
}
