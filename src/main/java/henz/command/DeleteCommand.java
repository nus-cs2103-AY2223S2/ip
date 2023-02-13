package henz.command;

import henz.dukeexception.CommandException;
import henz.dukeexception.StorageException;
import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.tasks.Task;
import henz.ui.Ui;

/**
 * DeleteCommand class extends from Command class.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor.
     * @param input the user command
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Deletes the task from the task list.
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
            String[] values = this.unwrap();
            int index = Integer.parseInt(values[0]) - 1;

            Task task = tasks.delete(index);
            storage.save(tasks);

            return "Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (CommandException | StorageException error) {
            return error.getMessage();
        }
    }
}
