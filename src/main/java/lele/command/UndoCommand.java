package lele.command;

import java.io.IOException;

import lele.exception.NoPreviousCommandException;
import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;

/**
 * Undo the previous commands by x number of versions (Only for CRUD commands)
 */
public class UndoCommand extends Command {
    private int index;

    public UndoCommand(int index) {
        this.index = index;
    }


    /**
     * Undo the previous command, updates the storage
     * and prints to user.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     * @return Output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, NoPreviousCommandException {
        taskList.restorePreviousList(this.index);
        storage.updateStorage(taskList);
        return ui.printUndo(index);
    }

    /**
     * Does not exit immediately after command.
     *
     * @return False, so that loop does not terminate.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
