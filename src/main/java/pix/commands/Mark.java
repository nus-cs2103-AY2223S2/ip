package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * Mark class to mark a task in the list of tasks.
 */
public class Mark extends Command {
    /** Index to mark. */
    private final int id;

    /**
     * Constructs a new Mark command.
     *
     * @param id Index to mark.
     */
    public Mark(int id) {
        this.id = id;
    }

    /**
     * Executes the mark command and displays the result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.markDone(this.id);
        data.saveToFile();
        return ui.mark(data.getData(this.id));
    }
}
