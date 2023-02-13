package dude.command;

import dude.storage.Storage;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to unmark Task.
 */
public class UndoCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        boolean result = tasks.undo();
        if (!result) {
            return ui.showUndoError();
        }
        storage.saveData(tasks);
        return ui.showUndo();
    }
}
