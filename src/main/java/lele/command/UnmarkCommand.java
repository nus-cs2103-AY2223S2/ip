package lele.command;

import java.io.IOException;

import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;


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
     * @return Output to user
     * @throws IOException When there is a problem with writing to the storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.unMarkStatus(index);
        storage.updateStorage(taskList);
        return ui.printUnMarkStatus(taskList, index);
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
