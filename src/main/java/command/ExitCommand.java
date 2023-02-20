package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Command class for exiting the program.
 */
public class ExitCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Exit the program.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Deletes a task in a file.
     * @return Returns the Bye message.
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public String process(TaskList taskList, Ui ui, Storage storage) {
        return ui.printByeMsg();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
