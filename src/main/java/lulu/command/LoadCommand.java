package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;
public class LoadCommand extends Command {
    public LoadCommand() {}

    /**
     * This method loads data from storage to tasks upon execution.
     *
     * @param tasks the TaskList to be loaded with data
     * @param ui the UI that displays messages
     * @param storage the Storage that loads data in the specified file location to tasks
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showLoad();
        storage.readSave(tasks);
        ui.showLoadComplete();
    }
}