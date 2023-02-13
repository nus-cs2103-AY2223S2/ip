package nemo.command;

import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to undo previous change to TaskList.
 *
 * @author Lian Kok Hai
 */
public class UndoCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NemoException {
        boolean didUndo = taskList.undo();
        storage.saveTaskList(taskList);
        return ui.getUndoMessage(didUndo);
    }
}
