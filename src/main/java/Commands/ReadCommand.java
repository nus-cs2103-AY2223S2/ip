package Commands;

import Exceptions.NoTaskException;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

/**
 * This class is used to produce the list of tasks.
 */
public class ReadCommand extends Command {
    /**
     * Show all the tasks in the database.
     * @param tasks The database.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            throw new NoTaskException(null);
        }
        ui.showList();
        ui.showAllTasks(tasks.getTasks());
    }

    /**
     * Check to continue the conversation.
     * @return True.
     */
    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
