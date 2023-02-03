package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;

/**
 * Represents the unmark command when the user enters "unmark".
 *
 */
public class UnmarkCommand extends Command{

    public int num;

    /**
     * Constructor for UnmarkCommand class.
     *
     * @param num Task number to be unmarked
     *
     */
    public UnmarkCommand(int num) {

        this.num = num;
    }

    /**
     * Executes the unmark command to print the unmark message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui,Storage storage) {
        storage.saveFile(tasks);
        return ui.printUnmark(tasks, this.num);
    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {

        return false;
    }
}
