package crystal.command;

import crystal.CrystalException;
import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
import crystal.task.Deadline;

/**
 * Represents the deadline command when the user enters "deadline".
 *
 */

public class DeadlineCommand extends Command{
    public String by;
    public String description;

    /**
     * Constructor for DeadlineCommand class.
     *
     * @param description Description of the task.
     * @param by The date time portion of the task.
     *
     */

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }


    /**
     * Executes the deadline command to print the deadline message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     * @throws CrystalException if the user input is incorrectly formatted
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CrystalException {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.printDeadline(tasks, deadline);
    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }
}
