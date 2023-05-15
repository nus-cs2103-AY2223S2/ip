package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class that acts as the
 * superclass for the numerous
 * command
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public abstract class Command {
    protected String[] command;

    /**
     * Constructor of Command
     * @param command Command from the user.
     */
    public Command(String[] command) {
        this.command = command;
    }

    /**
     * Method to execute the command.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     * @return Nothing
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }

    /**
     * Method to keep the chat running
     * if the command is not bye.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
