package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Abstract class about users' commands.
 */
abstract public class Command {
    private final String inputArr;

    /**
     * Class constructor.
     *
     * @param inputArr String from a user input.
     */
    public Command(String inputArr) {
        this.inputArr = inputArr;
    }

    public String getInputArr() {
        return inputArr;
    }

    /**
     * Creates a todo task, adds it to the task list and saves it to the file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file
     * @throws DukeException Checks the validation of input.
     */
    public abstract void process(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether exit the program.
     *
     * @return Boolean Exit the program.
     */
    public abstract boolean isExit();
}