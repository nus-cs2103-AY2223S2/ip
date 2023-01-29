package duke.commands;

import duke.utils.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.File;

public class AddCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Task task;
    private Storage storage;
    private File file;

    public AddCommand(Ui ui, TaskList taskList, Task task,
                      Storage storage, File file) {
        this.ui = ui;
        this.taskList = taskList;
        this.task = task;
        this.storage = storage;
        this.file = file;
    }

    @Override
    public void action() {
        taskList.addTask(task);
        ui.addResponse(task, taskList);
        storage.editStorage(taskList.getTaskList());
        storage.saveToFile(file);
    }
}
