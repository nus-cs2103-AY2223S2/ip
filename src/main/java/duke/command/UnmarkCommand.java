package duke.command;

import duke.*;
import duke.task.TaskList;

/**
 * Marks task as undone.
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    /**
     * Main constructor.
     *
     * @param taskNum number of the task to be marked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task as done in the task list.
     * Asks UI to print the marked task.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 0 || taskNum - 1 >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! The index to mark as done cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        tasks.markAsUndone(taskNum);
        Ui.showMessage("OK, I've marked this task as not done yet:\n\t" + tasks.get(taskNum));
        storage.save(tasks.getAllTasks());
    }
}