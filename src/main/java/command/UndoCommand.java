package command;

import duke.DukeException;
import task.TaskList;

/**
 * Command to undo the previous command (excluding List, Find, Exit, Undo) executed.
 */
public class UndoCommand extends Command {
    /**
     * Undoes whatever the previous command does to the list of tasks.
     * @param tasks The list of tasks affected by the previous command.
     * @return The return status of the result from executing this command in the form of a text message.
     * @throws DukeException If undoing the previous command throws a DukeException.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        return Command.undoPrevCommand(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String undo(TaskList tasks) throws DukeException {
        /* Just a placeholder. undo is not applicable here. */
        return null;
    }

    /**
     * Determines if the current command is an exit command.
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
