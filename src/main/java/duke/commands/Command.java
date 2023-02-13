package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class which contains the 2 functions all classes that
 * inherit from the Command Class must have.
 *
 * @author Cheam Jia Wei
 */
public abstract class Command {
    /**
     * Executes what the Command is supposed to do.
     *
     * @param taskList The TaskList that will be modified or accessed.
     * @param inter The Ui that will interact with the user.
     * @param store The storage that will help store the task into the data file if TaskList is modified.
     * @return The string Duke will respond with to the executed command.
     */
    public abstract String execute(TaskList taskList, Ui inter, Storage store) throws DukeException;

    /**
     * Returns a boolean if the Command class is an exit command.
     *
     * @return If command is an exit command.
     */
    public abstract boolean isExit();
}
