package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Abstract class about users' commands.
 */
abstract public class Command {
    private final String input;
    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    /**
     * Creates a todo task, adds it to the task list and saves it to the file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file
     * @throws DukeException Checks the validation of input.
     */
    public abstract String process(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether exit the program.
     *
     * @return Boolean Exit the program.
     */
    public abstract boolean isExit();
}