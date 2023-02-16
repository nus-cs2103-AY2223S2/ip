package duke.commands;

import duke.database.Database;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents an Edit command to change the details of an existing task in the taskList of Duke. */
public class EditCommand extends Command {

    private final int taskNumber;
    private final String newDetails;

    /**
     * Represents an Edit command to change the details of an existing task in the taskList of Duke.
     * @param taskNumber the number identifier of the task to be edited.
     * @param newDetails new details of the task.
     */
    public EditCommand(int taskNumber, String newDetails) {
        this.taskNumber = taskNumber;
        this.newDetails = newDetails;
    }

    /**
     * Executes the generated EditCommand by editing the relevant task's details in Duke to its new details and
     * giving it as a response to the Ui.
     *
     * @param taskList taskList of Duke.
     * @param ui user interface object of Duke.
     * @param database database of Duke.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws DukeException {
        assert this.isActive();
        Task task = taskList.getTask(this.taskNumber);
        String oldDetails = task.getDetails();
        task.setDetails(this.newDetails);

        ui.response(FRAME
                + "Ok I have changed Task " + this.taskNumber + " from\n"
                + "\"" + oldDetails + "\" to\n"
                + "\"" + this.newDetails + "\"\n"
                + FRAME);
    }
}
