package kira.command;

import kira.exception.KiraException;
import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for UNMARK.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs an executable to mark task at the index
     * as incomplete.
     *
     * @param index task index to be marked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        try {
            ui.unmarkMsg(taskList.unmark(this.index));
        } catch (KiraException e) {
            ui.errMsg(e.getMessage());
        }
        return true;
    }

}
