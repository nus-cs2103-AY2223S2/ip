package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.task.TaskList;
import james.jamesbot.Ui;

/**
 * The command to be executed. All specific commands are subclasses of this class.
 */
/**
 * The Command class is an abstract class that represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the user command.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If user command is used wrongly.
     */
    public abstract String execute (TaskList tasks, Ui ui, Storage storage) throws JamesException;

    /**
     * Returns whether the user command exits the program.
     * @return true if the command exits the program;
     *         false if the command does not exit the program.
     */
    public abstract boolean isExit();
}
