package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.FileNotFoundException;

/**
 * Command is the super class of all the commands
 */
public abstract class Command {

    /**
     * all commands should have this method
     * @param taskList
     * @param storage
     * @param ui
     * @throws FileNotFoundException
     */
    public abstract void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException;

    /**
     * checks if the command is a ExitCommand
     * @return true if it is a ExitCommand
     */
    public abstract boolean isExit();

}
