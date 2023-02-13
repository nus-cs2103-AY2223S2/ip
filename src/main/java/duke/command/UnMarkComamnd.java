package duke.command;

import duke.dukeexception.CommandException;
import duke.dukeexception.UnmarkIndexDoesNotExistException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * UnmarkCommand class extends Command
 */
public class UnmarkComamnd extends Command {

    /**
     * Constructor.
     * @param input the user input
     */
    public UnmarkComamnd(String input) {
        super(input);
    }

    /**
     * Unmarks the task by the 1-based specified id.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int id = Integer.parseInt(this.unwrap()[0]);
            int index = id - 1;
            if (index >= tasks.size()) {
                throw new UnmarkIndexDoesNotExistException("☹ OOPS!!! unmark index does not exist");
            }

            Task task = tasks.get(index);
            task.markAsNotDone();
            storage.save(tasks);

            return String.format("OK, I've marked this task as not done yet:\n%s\n", task);
        } catch (CommandException | UnmarkIndexDoesNotExistException error) {
            return error.getMessage();
        }
    }
}
