package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Abstract class for adding tasks.
 */
abstract class AddTaskCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public AddTaskCommand(String input) {
        super(input);
    }

    /**
     * Creates a task, adds it to the task list and saves it to the file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {

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
