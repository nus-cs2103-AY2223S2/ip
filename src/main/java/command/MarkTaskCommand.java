package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 *
 */
public class MarkTaskCommand extends Command {
    /**
     * Boolean to mark or unmark task.
     */
    private final boolean isMarked;

    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     * @param isMarked Boolean to mark or unmark task.
     */
    public MarkTaskCommand(String[] inputArr, boolean isMarked) {
        super(inputArr);
        this.isMarked = isMarked;
    }

    /**
     * Mark or unmark task depending on boolean passed from constructor.
     *
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = tasks.markTask(isMarked, this.getInputArr(), ui, storage);
        System.out.println(output);
        return output;
    }

    /**
     * Does not exit program.
     *
     * @return Boolean to exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isDataSourceChanged() {
        return false;
    }
}
