package lele.command;

import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;

import java.io.IOException;

/**
 * Handles the response for a status
 * marked as not done by the user.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Instantiates the index of the task to be unmarked.
     *
     * @param index Location of the task in the array list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Prints the unmarked task and updates the storage.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     * @throws IOException When there is a problem with
     * writing to the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
            ui.printUnMarkStatus(taskList, index);
            storage.updateStorage(taskList);
    }

    /**
     * Task marked as undone will not terminate
     * the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
