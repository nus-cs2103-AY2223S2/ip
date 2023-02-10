package kira.command;

import kira.exception.KiraException;
import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for MARK.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs an executable to mark task at the index
     * as completed.
     *
     * @param index task index to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        try {
            ui.markMsg(taskList.mark(this.index));
        } catch (KiraException e) {
            ui.errMsg(e.getMessage());
        }
        return true;
    }

}
