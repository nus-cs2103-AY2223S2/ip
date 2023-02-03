package lele.command;

import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;
import java.io.IOException;

/**
 * Handles the response for a status
 * marked as done by the user.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Instantiates the index of the task to be marked.
     *
     * @param index Location of the task in the array list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Prints the marked task and updates the storage.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     * @throws IOException When there is a problem with writing to the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.printMarkStatus(taskList, index);
        storage.updateStorage(taskList);
    }

    /**
     * Task marked as done will not terminate
     * the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
