package command;

import java.time.LocalDate;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Event;
import task.Task;

/**
 * Adds event tasks.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs AddEventCommand.
     *
     * @param description Description of the event task.
     * @param startDate Date that the event starts.
     * @param endDate Date that the event ends.
     */
    public AddEventCommand(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Adds an event task to the list of task, outputs message to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if user input is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = new Event(description, startDate, endDate);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputAddTask(t);
    }
}
