package duke.commands;

import duke.database.Database;
import duke.exception.tasklistexceptions.TaskListTaskNumberInvalidException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents a command to delete a task stored in from Duke. */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Represents a command to delete a task stored in from Duke.
     *
     * @param taskNumber The identifier of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the generated DeleteCommand by deleting the task related to the taskNumber
     * and gives a response to the Ui.
     *
     * @param taskList taskList of Duke.
     * @param ui user interface object of Duke.
     * @param database database of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws TaskListTaskNumberInvalidException {
        assert this.isActive();
        String taskDescription = taskList.getTask(taskNumber).getStatus();
        taskList.deleteTask(taskNumber);
        ui.response(FRAME
                + " Noted. I've removed this task:\n"
                + taskDescription
                + "\nNow you have " + taskList.length() + " tasks in the list." + "\n"
                + FRAME);
    }
}
