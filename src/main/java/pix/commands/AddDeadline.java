package pix.commands;

import pix.data.MyData;
import pix.tasks.Deadline;
import pix.ui.Ui;

/**
 * AddDeadline class to add deadline to list of tasks.
 */
public class AddDeadline extends Command {
    /** Deadline object to be added. */
    protected Deadline deadline;

    /**
     * Constructs a new AddDeadline command.
     *
     * @param description Description of the deadline.
     * @param by When the deadline is due.
     */
    public AddDeadline(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    /**
     * Executes the AddDeadline command and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.setData(deadline);
        data.saveToFile();
        return ui.add(deadline.toString(), data.len());
    }
}

