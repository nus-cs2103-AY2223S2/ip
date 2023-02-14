package duke.commands;

import duke.database.Database;
import duke.exception.TaskNumberNotFoundException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents a command for marking an existing task in the taskList of Duke. */
public class MarkCommand extends Command {

    private final int taskNumber;

    /**
     * Represents a command for marking an existing task in the taskList of Duke.
     *
     * @param taskNumber The identifier of the task.
     */
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the generated MarkCommand by marking the relevant task in Duke as complete and
     * giving it as a response to the Ui.
     *
     * @param taskList taskList of Duke.
     * @param ui user interface object of Duke.
     * @param database database of Duke.
     * @throws TaskNumberNotFoundException thrown when there is no task with that taskNumber
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws TaskNumberNotFoundException {
        assert this.isActive();
        Task task = taskList.getTask(this.taskNumber);
        task.setComplete();
        ui.response(FRAME
                + "Nice! I've marked this task as done:\n"
                + "[X] " + task.getDetails() + "\n"
                + FRAME);
    }

}
