package command;

import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Command class to exit program.
 */
public class ExitDukeCommand extends Command {
    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public ExitDukeCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    /**
     * Exits program.
     *
     * @return Boolean to exit program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
