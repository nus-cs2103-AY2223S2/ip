package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command to delete a particular task
 */
public class DeleteCommand extends Command {
    private final String index;

    /**
     * Returns a DeleteCommand with the index stored
     *
     * @param index Integer as String
     */
    public DeleteCommand(String index) {
        super("delete");
        this.index = index;
    }
    /**
     * Deletes the task at the target index from TaskList
     * Display the output via Ui showing the old task that was deleted
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui Ui the user interface to interact with the user
     * @param storage Storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if the String index is not an integer OR if index is not in range of size of TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int idx = Integer.parseInt(this.index);
            String taskDescription = tasks.delete(idx);
            ui.print(String.format(
                    "Noted, I've removed this task: \n\t%s\nNow you have %d tasks in this list.",
                    taskDescription,
                    tasks.size()));
            storage.saveList(tasks);
        } catch (NumberFormatException notANumber) {
            throw new DukeException("Please enter a valid number");
        }
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int idx = Integer.parseInt(this.index);
            String taskDescription = tasks.delete(idx);
            storage.saveList(tasks);
            return String.format(
                    "Noted, I've removed this task: \n\t%s\nNow you have %d tasks in this list.",
                    taskDescription,
                    tasks.size());
        } catch (NumberFormatException notANumber) {
            throw new DukeException("Please enter a valid number");
        }
    }
}
