package clippy.command;

import java.time.LocalDate;

import clippy.storage.Storage;
import clippy.task.Event;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `event` command.
 *
 * @author chunzkok
 */
public class AddEventCommand extends AddCommand {
    private String description;
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates a new AddEventCommand instance.
     *
     * @param description Description of the event to be added.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds a new Event to the TaskList.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.add(new Event(description, from, to));
        super.printCreatedTaskStatus(taskList, ui);
    }
}
