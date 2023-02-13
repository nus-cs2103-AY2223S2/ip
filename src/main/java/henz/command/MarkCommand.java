package henz.command;

import henz.henzexception.CommandException;
import henz.henzexception.MarkIndexDoesNotExistException;
import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.tasks.Task;
import henz.ui.Ui;

/**
 * MarkCommand class extends from Command class.
 */
public class MarkCommand extends Command {

    /**
     * Constructor.
     * @param input the user input
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Marks the task by the 1-based specified id.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        try {
            int id = Integer.parseInt(this.unwrap()[0]);
            int index = id - 1;
            if (index >= tasks.size()) {
                throw new MarkIndexDoesNotExistException("☹ OOPS!!! mark index does not exist");
            }

            Task task = tasks.get(index);
            task.markAsDone();
            storage.save(tasks);

            return String.format("Nice! I've marked this task as done:\n%s", task);
        } catch (CommandException | MarkIndexDoesNotExistException error) {
            return error.getMessage();
        }
    }
}
