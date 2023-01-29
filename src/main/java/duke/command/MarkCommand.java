package command;

import dukeexception.CommandException;
import dukeexception.MarkIndexDoesNotExistException;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

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
     * Mark the task by the 1-based specified id.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int id = Integer.parseInt(this.unwrap()[0]);
            int index = id - 1;
            if (index >= tasks.size()) {
                throw new MarkIndexDoesNotExistException("☹ OOPS!!! mark index does not exist");
            }

            Task task = tasks.get(index);
            task.markAsDone();
            ui.showMessage(String.format("Nice! I've marked this task as done:\n%s", task));

            storage.save(tasks);
        } catch (CommandException | MarkIndexDoesNotExistException error) {
            ui.showError(error);
        }
    }
}
