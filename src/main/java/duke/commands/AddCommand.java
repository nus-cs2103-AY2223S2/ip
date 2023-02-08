package duke.commands;

import java.io.File;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;


/**
 * AddCommand represents a command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Task task;
    private Storage storage;
    private File file;

    /**
     * Creates a AddCommand to add a ToDo, Event or Deadline to the TaskList.
     *
     * @param ui The ui used.
     * @param taskList The TaskList to add the task to.
     * @param task The task to be added.
     * @param storage The storage to store the changes.
     * @param file The specified file to store the changes.
     */
    public AddCommand(Ui ui, TaskList taskList, Task task,
                      Storage storage, File file) {
        this.ui = ui;
        this.taskList = taskList;
        this.task = task;
        this.storage = storage;
        this.file = file;
    }

    /**
     * Adds the task to the TaskList.
     */
    @Override
    public String action() {
        taskList.addTask(task);
        storage.editStorage(taskList.getTaskList());
        storage.saveToFile(file);
        return ui.addResponse(task, taskList);
    }
}
