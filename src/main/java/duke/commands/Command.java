package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;

/**
 * The abstract class for commands.
 */
public abstract class Command {
    /**
     * The abstract method to operate the commands.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public abstract String operate(TaskList lst, Ui ui, Storage storage);

    /**
     * To check whether the command is a bye command.
     *
     * @return a boolean showing whether the command is bye
     */
    public boolean isBye() {
        return false;
    }
}
