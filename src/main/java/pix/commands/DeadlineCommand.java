package pix.commands;

import pix.data.MyData;
import pix.tasks.Deadline;
import pix.ui.Ui;

/**
 * DeadlineCommand class to add deadline to list of tasks.
 */
public class DeadlineCommand extends Command {
    /** Deadline object to be added. */
    protected Deadline deadline;

    /**
     * Constructs a new DeadlineCommand.
     *
     * @param description Description of the deadline.
     * @param dueDate When the deadline is due.
     */
    public DeadlineCommand(String description, String dueDate) {
        this.deadline = new Deadline(description, dueDate);
    }

    /**
     * Executes the DeadlineCommand and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.addTask(deadline);
        data.saveToFile();
        return ui.add(deadline.toString(), data.len());
    }
}

