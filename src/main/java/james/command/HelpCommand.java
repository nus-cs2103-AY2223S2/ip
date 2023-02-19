package james.command;

import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.TaskList;

/**
 * HelpCommand class that list down all commands when user types "help"
 */
public class HelpCommand extends Command {
    public static final String COMMAND = "help";

    public static final String MESSAGE = COMMAND + ": Displays all commands available.\n";

    /**
     * Executes the HelpCommand which is to list all tasks stored in user's hard disk.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.e.
     * @param storage The task list that is stored in the storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayCommands();
    }

    /**
     * Returns whether HelpCommand exits the program.
     *
     * @return false as HelpCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
