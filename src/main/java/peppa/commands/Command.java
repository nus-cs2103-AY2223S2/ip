package peppa.commands;

import peppa.PeppaException;
import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

/**
 * An interface that represents a command entered by the user.
 */

public interface Command {

    /**
     * Performs the action corresponding to the command.
     *
     * @param taskList List of tasks.
     * @param screen Command-line user interface.
     * @param storage File-like object for reading and storing data.
     * @return Message that is output and shown to the user.
     * @throws PeppaException
     */
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException;
    public boolean isExit();
}
