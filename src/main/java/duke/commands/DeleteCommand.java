package duke.commands;

import java.io.File;

import duke.exceptions.InvalidCommandValueException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;


/**
 * DeleteCommand represents a command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private int index;
    private Storage storage;
    private File file;

    /**
     * Creates a DeleteCommand to delete a ToDo, Event or Deadline from the TaskList.
     *
     * @param ui The ui used
     * @param taskList The TaskList to delete the task from.
     * @param index The specified task to be deleted.
     * @param storage The storage to store the changes.
     * @param file The specified file to store the changes.
     * @throws InvalidCommandValueException If a delete command specify a wrong index.
     */
    public DeleteCommand(Ui ui, TaskList taskList, int index,
                         Storage storage, File file)
            throws InvalidCommandValueException {
        if (index + 1 > taskList.getSize() || index < 0) {
            throw new InvalidCommandValueException();
        }
        this.ui = ui;
        this.taskList = taskList;
        this.index = index;
        this.storage = storage;
        this.file = file;
    }

    /**
     * Delete the task from the TaskList.
     */
    @Override
    public String action() {
        Task task = taskList.removeTask(index);
        assert task != null;
        storage.editStorage(taskList.getTaskList());
        storage.saveToFile(file);
        return ui.deleteResponse(task, taskList);
    }
}
