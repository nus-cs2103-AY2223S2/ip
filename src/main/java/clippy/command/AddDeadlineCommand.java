package clippy.command;

import java.time.LocalDate;

import clippy.storage.Storage;
import clippy.task.Deadline;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `deadline` command.
 *
 * @author chunzkok
 */
public class AddDeadlineCommand extends AddCommand {
    private String description;
    private LocalDate by;

    /**
     * Creates a new AddDeadlineCommand instance.
     *
     * @param description A description of the task to be added.
     * @param by The deadline for the task to be added.
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a new Deadline to the TaskList.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.add(new Deadline(description, by));
        super.printCreatedTaskStatus(taskList, ui);
    }
}
