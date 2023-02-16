package duke.commands;

import duke.database.Database;
import duke.exception.marktaskexceptions.MarkTaskNumberInvalidException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents a command for unmarking an existing task in the taskList of Duke. */
public class UnmarkCommand extends Command {

    private final int taskNumber;

    /**
     * Represents a command for unmarking an existing task in the taskList of Duke.
     *
     * @param taskNumber The identifier of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the generated MarkCommand by marking the relevant task in Duke as incomplete and
     * giving it as a response to the Ui.
     *
     * @param taskList taskList of Duke.
     * @param ui user interface object of Duke.
     * @param database database of Duke.
     * @throws MarkTaskNumberInvalidException thrown when there is no task with that taskNumber
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws MarkTaskNumberInvalidException {
        assert this.isActive();
        Task task = taskList.getTask(this.taskNumber);
        task.setIncomplete();
        ui.response(FRAME
                + "OK, I've marked this task as not done yet:\n"
                + "[ ] " + task.getDetails() + "\n"
                + FRAME);
    }
}
