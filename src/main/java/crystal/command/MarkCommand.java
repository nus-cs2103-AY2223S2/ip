package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;

/**
 * Represents the mark command when the user enters "mark".
 *
 */

public class MarkCommand extends Command {
    public int num;

    /**
     * Constructor for MarkCommand class.
     *
     * @param num Task number to be marked
     *
     */

    public MarkCommand(int num) {

        this.num = num;
    }

    /**
     * Executes the event command to print the event message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui,Storage storage) {
        storage.saveFile(tasks);
        return ui.printMark(tasks, this.num);
    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }

}
