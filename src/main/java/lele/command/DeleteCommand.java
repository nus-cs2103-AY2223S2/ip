package lele.command;

import java.io.IOException;

import lele.storage.Storage;
import lele.task.Task;
import lele.task.TaskList;
import lele.ui.Ui;


/**
 * Handles actions to take when user requests
 * for a task to be deleted.
 */
public class DeleteCommand extends Command {
    private final int inputIndex;

    /**
     * Instantiates the index of the task in the
     * task list.
     * @param inputIndex Location of the task to be deleted
     *                  in the task list.
     */
    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    /**
     * Deletes task from task list, updates the storage then
     * printing to the command line.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     * @return Output to user.
     * @throws IOException When there is a problem with writing to the file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task t = taskList.deleteTask(inputIndex);
        storage.updateStorage(taskList);
        return ui.printDelete(taskList, t);
    }

    /**
     * Deleting the task will not terminate the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
