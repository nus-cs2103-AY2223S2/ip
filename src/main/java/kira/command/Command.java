package kira.command;

import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Abstract class to signify all command executables.
 */
public abstract class Command {

    /**
     * Executes the command function.
     *
     * @param ui user interaction to print outputs
     * @param tasklist tasklist to be processed
     * @return boolean to signify whether the bot should continue functioning
     */
    public abstract boolean execute(Ui ui, TaskList tasklist);

}
