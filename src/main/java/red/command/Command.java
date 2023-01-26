package red.command;

import red.exception.RedException;
import red.storage.Storage;
import red.task.TaskList;
import red.ui.UI;

/**
 * An abstract instruction class encapsulating a user input instruction in Duke, which can be extended
 * by more specific input instructions like addToDoInstruction, ExitInstructions, etc.
 */

public abstract class Command {
    /**
     * Indicates whether the Command is the Exit Command.
     *
     * @return whether the command is the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute the respective instructions.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     * @throws RedException Throws Exception when the user inputs invalid instruction or
     * when encountering issues reading or writing to the storage file.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws RedException;
}