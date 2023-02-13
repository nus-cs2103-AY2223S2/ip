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
     * @param inputArr String from a user input.
     */
    public ListCommand(String inputArr) {
        super(inputArr);
    }

    /**
     * Lists all tasks.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.listTask(this.getInputArr());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}