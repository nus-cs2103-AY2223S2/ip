package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

import java.util.Date;

/**
 * Adds a Deadline type task.
 */
public class AddDeadlineCommand extends Command {
    public String description;
    public String deadlineTime;
    public Date dueDate;

    /**
     * Main constructor
     *
     * @param description description of the task.
     * @param deadlineTime string of the due date.
     */
    public AddDeadlineCommand(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Main constructor
     *
     * @param description description of the task.
     * @param dueDate due date of the event.
     */
    public AddDeadlineCommand(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Adds a Deadline type task.
     * Ask the UI to print the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        if (dueDate == null) {
            deadline = new Deadline(description, deadlineTime);
        } else {
            deadline = new Deadline(description, dueDate);
        }
        tasks.add(deadline);
        Ui.showAddMessage(deadline, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}