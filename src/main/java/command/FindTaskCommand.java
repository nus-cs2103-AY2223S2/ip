package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Command class for finding some tasks.
 */
public class FindTaskCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public FindTaskCommand(String input) {
        super(input);
    }

    /**
     * Finds any tasks that contain the description.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Deletes a task in a file.
     * @return Returns a list of tasks that contains the input.
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public String process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.findTask(this.getInput(), ui);
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
