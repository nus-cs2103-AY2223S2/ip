package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This is a command to terminate Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Checks if Duke should terminate after this command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints out the exit message for Duke.
     *
     * @param taskList the TaskList storing all Task.
     * @param ui the Ui for handling inputs/outputs.
     * @param storage the Storage responsible for reading/writing data.
     * @return the message shown to the user after execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return Ui.showExit();
    }

    /**
     * Checks if the given Object equals to this.
     *
     * @param o the Object being compared.
     * @return true if o is an instance of this.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof ExitCommand;
    }
}
