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
     * @param taskList Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTask(isMarked, inputArr, ui);
        storage.writeData(taskList);
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
}
