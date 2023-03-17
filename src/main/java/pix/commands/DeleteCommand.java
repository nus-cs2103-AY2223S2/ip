package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * DeleteCommand class to delete task from list of tasks.
 */
public class DeleteCommand extends Command {
    /** Index to delete task. */
    private final int id;

    /**
     *  Constructs a new DeleteCommand.
     *
     * @param id Index to delete task.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the DeleteCommand and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        int itemCount = data.len() - 1;
        String deleteStringToDisplay = ui.delete(data.getTaskAtIndex(this.id), itemCount);
        data.deleteTask(id);
        data.saveToFile();
        return deleteStringToDisplay;
    }
}
