package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int inputIndex;

    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task t = taskList.deleteTasks(inputIndex);
        storage.updateStorage(taskList);
        ui.printDelete(taskList, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
