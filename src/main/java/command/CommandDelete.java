package command;

import duke.DukeException;
import duke.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command to delete a task.
 */
public class CommandDelete extends Command {

    private final TaskList taskList;
    private final String index;
    private final Storage storage;

    /**
     * Constructor for CommandDelete.
     *
     * @param taskList List of all tasks.
     * @param index Index of task, starting from 1.
     */
    public CommandDelete(TaskList taskList, String index, Storage storage) {
        this.taskList = taskList;
        this.index = index;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        Task taskRemoved = this.removeTaskAt(this.index);
        this.updateFile();
        return this.getConfirmationMessageOf(taskRemoved);
    }

    private void updateFile() throws DukeException {
        this.storage.overwriteFile(this.taskList);
    }

    private Task removeTaskAt(String index) throws DukeException {
        int i = this.getIndex(index);
        return this.taskList.removeTaskAt(i);
    }

    private int getIndex(String index) throws DukeException {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private String getConfirmationMessageOf(Task taskRemoved) {
        return Ui.getDeleteMessageWithAttitudeOf(taskRemoved);
    }
}
