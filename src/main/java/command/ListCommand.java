package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Command class for listing tasks.
 */
public class ListCommand extends Command {

    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Lists all tasks.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file.
     * @return Returns a list of tasks are stored.
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public String process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.listTask(this.getInput());
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
