package duke.commands;

import duke.exceptions.InvalidCmdValueException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;

import java.io.File;

public class MarkCommand extends Command {

    private Ui ui;
    private TaskList taskList;
    private int index;
    private Storage storage;
    private File file;

    public MarkCommand(Ui ui, TaskList taskList, int index,
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

    @Override
    public void action() {
        Task task = taskList.markTask(index);
        ui.markResponse(task);
        storage.editStorage(taskList.getTaskList());
        storage.saveToFile(file);
    }
}
