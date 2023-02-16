package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Command class for showing invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public InvalidCommand(String input) {
        super(input);
    }

    /**
     * Deletes a task, both of task list and file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Deletes a task in a file.
     * @return Returns the invalid input message.
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public String process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.printWrongMsg();
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
