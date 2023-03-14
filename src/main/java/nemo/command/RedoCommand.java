package nemo.command;

import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to redo previously undone change to TaskList.
 *
 * @author Lian Kok Hai
 */
public class RedoCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NemoException {
        boolean didRedo = taskList.redo();
        storage.saveTaskList(taskList);
        return ui.getRedoMessage(didRedo);
    }
}
