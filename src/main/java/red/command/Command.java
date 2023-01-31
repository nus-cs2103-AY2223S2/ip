package red.command;

import red.exception.RedException;

import red.storage.Storage;

import red.task.TaskList;

import red.ui.UI;

/**
 * This abstract class is the parent class of classes that alter the current list of tasks.
 */

public abstract class Command {
    /**
     * Indicates whether the Command is the Exit Command.
     *
     * @return true if command is the exit command and false if it is not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the specified command.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     * @throws RedException Throws Exception when the user inputs invalid instruction.
     *
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws RedException;
}