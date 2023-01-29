package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Deadline is a command that adds a new deadline task
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Contructor for DeadlineCommand
     *
     * @param description Description of deadline.
     * @param by          Due date of deadline.
     */
    public DeadlineCommand(String description, String by) {
        super(false);
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a deadline task into the tasklist.
     *
     * @param task    Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui      Deals with interactions with user.
     * @throws DukeException if command cannot be recognised.
     */
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.addDeadline(description, by);
        int size = task.getSize();
        Task temp = task.getTask(size - 1);
        ui.showAdd(temp, size);
        storage.saveTasksToFile(task.getListOfTasks());
    }
}
