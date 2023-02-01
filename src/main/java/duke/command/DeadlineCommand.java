package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Executable command to create deadline.
 *
 * @author Guo-KeCheng
 */
public class DeadlineCommand extends Command {
    private String command;
    private TaskList taskList;
    private Ui ui;

    /**
     * DeadlineCommand constructor
     *
     * @param command Entire line of user input
     * @param taskList Existing taskList
     * @param ui Shared Ui object
     */
    public DeadlineCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Instantiate deadline base on the string command
     * Checks for valid TaskName and EndDate
     *
     * @return false as program will not terminate yet
     * @throws DukeException if input is incorrect
     */
    @Override
    public boolean execute() throws DukeException {
        String taskName = getTaskName("deadline", command);
        String endDate = getEndDate("deadline", command);

        try {
            Deadline deadline = new Deadline(taskName, endDate);
            taskList.add(deadline);
            ui.printAddedTask(deadline, taskList);
        } catch (DateTimeParseException e) {
            ui.showInvalidTimeError();
        }
        return false;
    }
}
