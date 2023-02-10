package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * This method lists the tasks in tasks upon execution.
     *
     * @param tasks   the TaskList to be listed
     * @param ui      the UI that displays messages
     * @param storage the Storage is not relevant in this command
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.showContainer(ui.listText(), tasks.printList());
    }
}
