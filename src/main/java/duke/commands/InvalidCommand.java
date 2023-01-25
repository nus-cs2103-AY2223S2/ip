package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This is a command that informs the user for entering an invalid input
 */
public class InvalidCommand extends Command {

    /**
     * Constructor of InvalidCommand class
     */
    public InvalidCommand() {
        super();
    }

    /**
     * Checks if Duke should terminate after this command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out a message informing the user of invalid input
     *
     * @param taskList the TaskList storing all Task
     * @param ui the Ui for handling inputs/outputs
     * @param storage the Storage responsible for reading/writing data
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.showInvalidCommand();
    }

    /**
     * Checks if the given Object is the same as this
     *
     * @param o the Object being compared
     * @return true if o is an instance of this
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof InvalidCommand;
    }
}
