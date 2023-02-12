package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * The execute method is responsible for executing the command.
     *
     * @param tasks the task list where the command will be executed on
     * @param ui the user interface where the result of the command will be displayed
     * @param storage the storage where the task list will be saved
     * @throws DukeException if there is any error during the execution of the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
