package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Marks task as done.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * Main constructor.
     *
     * @param taskNum number of the task to be marked.
     */
    public MarkCommand(int taskNum) {
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

        tasks.markAsDone(taskNum);
        Ui.showMessage("Nice! I've marked this task as done:\n\t" + tasks.get(taskNum));
        storage.save(tasks.getAllTasks());
    }
}