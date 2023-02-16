package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Abstract class for all commands from user.
 */
public abstract class Command {
    private final String[] inputArr;

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
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * @return Boolean to exit program.
     */
    public abstract boolean isExit();

    public abstract boolean isDataSourceChanged();

    public String[] getInputArr() {
        return inputArr;
    }
}
