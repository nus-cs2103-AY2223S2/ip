package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

/**
 * Command class for adding deadline tasks.
 */
public class DeleteTaskCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public DeleteTaskCommand(String input) {
        super(input);
    }

    /**
     * Deletes a task, both of task list and file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Deletes a task in a file.
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(this.getInput(), ui, storage);
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