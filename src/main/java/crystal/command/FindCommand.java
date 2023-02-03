package crystal.command;

import crystal.Storage;
import crystal.TaskList;
import crystal.Ui;

/**
 * Represents the find command when the user enters "find".
 *
 */
public class FindCommand extends Command {

    public String word;

    /**
     * Constructor for FindCommand class
     *
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the find command to print the find message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printFind(tasks, this.word);
    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }
}
