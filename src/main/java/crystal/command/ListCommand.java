package crystal.command;

import crystal.Storage;
import crystal.TaskList;
import crystal.Ui;


/**
 * Represents the list command when the user enters "list".
 *
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to print the list message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
        return ui.printList(tasks);
    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }
}
