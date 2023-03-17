package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * MarkCommand class to mark a task in the list of tasks.
 */
public class MarkCommand extends Command {
    /** Index to mark. */
    private final int id;

    /**
     * Constructs a new MarkCommand.
     *
     * @param id Index to mark.
     */
    public MarkCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the MarkCommand and displays the result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.markDone(this.id);
        data.saveToFile();
        return ui.mark(data.getTaskAtIndex(this.id));
    }
}
