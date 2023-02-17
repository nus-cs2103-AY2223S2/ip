package duke.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Executable command to create deadline.
 *
 * @author Guo-KeCheng
 */
public class DeadlineCommand extends Command {
    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    private final Storage storage;

    /**
     * DeadlineCommand constructor
     *
     * @param input    Entire line of user input
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public DeadlineCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Instantiate deadline base on the string command
     * Checks for valid TaskName and EndDate
     *
     * @return false as program will not terminate yet
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        String taskName = getTaskName("deadline", input);
        String endDate = getEndDate("deadline", input);

        try {
            Deadline deadline = new Deadline(taskName, endDate);
            taskList.add(deadline);

            try {
                storage.save(taskList);
            } catch (IOException e) {
                return ui.showError(e.getMessage());
            }

            return ui.printAddedTask(deadline, taskList);
        } catch (DateTimeParseException e) {
            return ui.showInvalidTimeError();
        }
    }
}
