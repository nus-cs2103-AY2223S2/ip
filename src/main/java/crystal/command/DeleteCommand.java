package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;

/**
 * Represents the delete command when the user enters "delete".
 *
 */
public class DeleteCommand extends Command{

    public int num;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param num Task number to be deleted
     *
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the delete command to print the delete message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui,Storage storage) {
        storage.saveFile(tasks);
        return ui.printDelete(tasks, this.num);
    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }
}
