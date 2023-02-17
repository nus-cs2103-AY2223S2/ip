package command;

import java.time.LocalDate;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Deadline;
import task.Task;

/**
 * Adds deadline tasks.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate dueDate;

    /**
     * Constructs AddDeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param dueDate Deadline of the task.
     */
    public AddDeadlineCommand(String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;

    }

    /**
     * Adds a deadline task to the list of task, outputs message to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if user input is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = new Deadline(description, dueDate);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputAddTask(t);
    }
}
