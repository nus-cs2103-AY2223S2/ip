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
     * A Constructor for DeadlineCommand
     *
     * @param description Description of deadline.
     * @param by Due date of deadline.
     */
    public DeadlineCommand(String description, String by) {
        super(false);
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a deadline task into the tasklist.
     *
     * @param tasks Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if command cannot be recognised.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addDeadline(description, by);
        int size = tasks.getSize();
        Task temp = tasks.getTask(size - 1);
        storage.saveTasksToFile(tasks.getListOfTasks());
        return ui.showAdd(temp, size);

    }
}
