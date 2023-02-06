package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * Delete class to delete task from list of tasks.
 */
public class Delete extends Command {
    /** Index to delete task. */
    private final int id;

    /**
     *  Constructs a new Delete command.
     *
     * @param id Index to delete task.
     */
    public Delete(int id) {
        this.id = id;
    }

    /**
     * Executes the delete command and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        int itemCount = data.len() - 1;
        String deleteStringToDisplay = ui.delete(data.getData(this.id), itemCount);
        data.deleteData(id);
        data.saveToFile();
        return deleteStringToDisplay;
    }
}
