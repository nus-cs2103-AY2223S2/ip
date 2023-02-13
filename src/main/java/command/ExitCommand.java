package command;

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
     * @param inputArr String from a user input.
     */
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMsg();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}