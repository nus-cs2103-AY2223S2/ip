package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

/**
 * This command is used to load tasks from the user's Storage.
 * When executed, the TaskList is loaded from the file specified in Storage.
 */
public class LoadCommand extends Command {
    public LoadCommand() {
    }

    /**
     * This method loads data from storage to tasks upon execution.
     *
     * @param tasks   the TaskList to be loaded with data
     * @param ui      the UI that displays messages
     * @param storage the Storage that loads data in the specified file location to tasks
     * @return a String that displays that the loading is complete.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.readSave(tasks);
        return ui.showContainer(ui.showLoadComplete());
    }
}