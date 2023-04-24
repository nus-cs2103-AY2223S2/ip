package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.storage.Storage;

/**
 * Results in the program saving current tasks and exiting when this command
 * is executed.
 */
public class ByeCommand extends Command {
    /**
     * Saves current tasks into storage and displays exit message.
     * Displays error message if error encountered while saving tasks.
     *
     * @param tasks List of all current tasks.
     * @param storage Storage for saving tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String commandResult;
        try {
            storage.save(tasks);
            commandResult = "Tasks saved successfully.\nTwoFive hopes to see you again soon, bye!";
            System.exit(0);
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
