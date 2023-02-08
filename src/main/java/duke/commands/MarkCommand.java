package duke.commands;

import java.io.File;

import duke.exceptions.InvalidCommandValueException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;


/**
 * MarkCommand represents a command to mark a task on the TaskList.
 */
public class MarkCommand extends Command {

    private Ui ui;
    private TaskList taskList;
    private int index;
    private Storage storage;
    private File file;

    /**
     * Creates a MarkCommand to mark a ToDo, Event or Deadline on the TaskList.
     *
     * @param ui The ui used.
     * @param taskList The TaskList to mark the task.
     * @param index The specified task to be marked.
     * @param storage The storage to store the changes.
     * @param file The specified file to store the changes.
     */
    public MarkCommand(Ui ui, TaskList taskList, int index,
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
     * Marks the task on the TaskList.
     */
    @Override
    public String action() {
        Task task = taskList.markTask(index);
        storage.editStorage(taskList.getTaskList());
        storage.saveToFile(file);
        return ui.markResponse(task);
    }
}
