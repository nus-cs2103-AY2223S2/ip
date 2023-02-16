package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Abstract command class for adding tasks.
 */
abstract class AddTaskCommand extends Command {
    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public AddTaskCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Does not exit program.
     *
     * @return Boolean to exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isDataSourceChanged() {
        return false;
    }
}
