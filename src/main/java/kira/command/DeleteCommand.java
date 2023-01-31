package kira.command;

import kira.exception.KiraException;
import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for DELETE.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs an executable to remove a task from the
     * task list.
     *
     * @param index task index to be removed
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        try {
            ui.deleteMsg(taskList.delete(this.index));
        } catch (KiraException e) {
            ui.errMsg(e.getMessage());
        }
        return true;
    }

}
