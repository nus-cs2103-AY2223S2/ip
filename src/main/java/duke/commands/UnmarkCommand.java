package duke.commands;

import duke.exceptions.InvalidCmdValueException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;

import java.io.File;

/**
 * UnmarkCommand represents a command to unmark a task on the TaskList.
 */
public class UnmarkCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private int index;
    private Storage storage;
    private File file;

    /**
     * Creates a UnmarkCommand to unmark a ToDo, Event or Deadline on the TaskList.
     * @param ui The ui used.
     * @param taskList The TaskList to unmark the task.
     * @param index The specified task to be unmarked.
     * @param storage The storage to store the changes.
     * @param file The specified file to store the changes.
     */
    public UnmarkCommand(Ui ui, TaskList taskList, int index,
                         Storage storage, File file)
            throws InvalidCmdValueException {
        if (index + 1 > taskList.getSize() || index < 0) {
            throw new InvalidCmdValueException();
        }
        this.ui = ui;
        this.taskList = taskList;
        this.index = index;
        this.storage = storage;
        this.file = file;
    }

    /**
     * Unmark the task on the TaskList.
     */
    @Override
    public String action() {
        Task task = taskList.unmarkTask(index);
        storage.editStorage(taskList.getTaskList());
        storage.saveToFile(file);
        return ui.unmarkResponse(task);
    }
}
