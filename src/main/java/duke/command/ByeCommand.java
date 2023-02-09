package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Prints out goodbye message.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! Hope to see you again soon!";
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
