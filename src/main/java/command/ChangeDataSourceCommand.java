package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Command class for changing data source.
 */
public class ChangeDataSourceCommand extends Command {
    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public ChangeDataSourceCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * Change data source to user preference.
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = storage.changeDataSource(this.getInputArr());
        System.out.println(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isDataSourceChanged() {
        return true;
    }
}
