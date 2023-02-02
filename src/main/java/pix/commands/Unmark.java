package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * Unmark class which unmarks a task from the list of tasks.
 */
public class Unmark extends Command {
    /** Index to remove mark. */
    private final int id;

    /**
     * Constructs a new Unmark command.
     *
     * @param id Index to remove mark.
     */
    public Unmark(int id) {
        this.id = id;
    }

    /**
     * Executes the unmark command and displays the result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.markUndone(this.id);
        data.saveToFile();
        return ui.unmark(data.getData(this.id));
    }
}
