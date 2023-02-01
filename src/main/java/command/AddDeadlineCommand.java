package command;

import java.time.LocalDate;

import main.TaskList;
import main.Storage;
import main.DukeException;
import main.Ui;
import task.Deadline;
import task.Task;

/**
 * Adds deadline tasks.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate endDate;

    /**
     * Constructs AddDeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param endDate Deadline of the task.
     */
    public AddDeadlineCommand(String description, LocalDate endDate) {
        this.description = description;
        this.endDate = endDate;

    }

    /** Adds a deadline task to the list of task, outputs message to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if user input is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = new Deadline(description, endDate);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputAddTask(t);
    }

}
