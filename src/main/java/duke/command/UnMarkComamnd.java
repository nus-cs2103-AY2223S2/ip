package duke.command;

import duke.dukeexception.CommandException;
import duke.dukeexception.UnmarkIndexDoesNotExistException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * UnMarkCommand class extends Command
 */
public class UnMarkComamnd extends Command {

    /**
     * Constructor.
     * @param input the user input
     */
    public UnMarkComamnd(String input) {
        super(input);
    }

    /**
     * Unmark the task by the 1-based specified id.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int id = Integer.parseInt(this.unwrap()[0]);
            int index = id - 1;
            if (index >= tasks.size()) {
                throw new UnmarkIndexDoesNotExistException("☹ OOPS!!! unmark index does not exist");
            }

            Task task = tasks.get(index);
            task.markAsNotDone();
            ui.showMessage(String.format("OK, I've marked this task as not done yet:\n%s\n", task));

            storage.save(tasks);
        } catch (CommandException | UnmarkIndexDoesNotExistException error) {
            ui.showError(error);
        }
    }
}
