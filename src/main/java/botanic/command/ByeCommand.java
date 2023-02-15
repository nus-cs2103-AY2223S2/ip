package botanic.command;

import botanic.storage.Storage;
import botanic.task.TaskList;
import botanic.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Prints out goodbye message.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return "Bye, hApple a-maize-zing day!\n" + "See you again soon!";
    }

    /**
     * Returns whether the command requires the program to exit.
     *
     * @return True indicating that the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
