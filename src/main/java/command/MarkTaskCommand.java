package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Command class for mark or unmarking tasks.
 */
public class MarkTaskCommand extends Command {

    private final boolean isMarked;

    /**
     * Class constructor.
     *
     * @param input String from a user input.
     * @param isMarked Boolean to mark or unmark the task.
     */
    public MarkTaskCommand(String input, boolean isMarked) {
        super(input);
        this.isMarked = isMarked;
    }

    /**
     * Marks or unmarks a task.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public String process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.markTask(isMarked, this.getInput(), ui, storage);
    }

    /**
     * Checks whether exit the program.
     *
     * @return Boolean Exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}