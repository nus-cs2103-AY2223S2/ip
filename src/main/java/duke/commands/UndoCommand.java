package duke.commands;

import duke.exceptions.DukeEmptyListException;
import duke.exceptions.DukeEmptyUndoHistoryException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The {@code Command} class for the {@code undo} command.
 */
public class UndoCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * @throws DukeEmptyUndoHistoryException If there is nothing to undo.
     * @throws DukeEmptyListException        If the list is empty after a successful
     *                                       undo.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeEmptyUndoHistoryException, DukeEmptyListException {

        taskList.restorePreviousState();
        storage.saveTaskList(taskList);

        String listCommandOutput = new ListCommand().execute(taskList, ui, storage);
        return "Undo success. Automatically running `list` command...\n" + listCommandOutput;
    }

    public boolean isByeCommand() {
        return false;
    }
}
