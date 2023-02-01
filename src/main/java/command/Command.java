package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Abstract class for all commands from user.
 */
abstract public class Command {
    String[] inputArr;

    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public Command(String[] inputArr) {
        this.inputArr = inputArr;
    }

    /**
     * Executes commands based on user input.
     *
     * @param taskList Stores all tasks.
     * @param ui Handles all user interaction.
     * @param storage Handles all storage of tasks in a file.
     * @throws DukeException Checks if input is valid.
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * @return Boolean to exit program.
     */
    abstract public boolean isExit();
}
