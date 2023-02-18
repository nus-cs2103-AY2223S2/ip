package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

/**
 * This command is used to list tasks from the user's TaskList.
 * When executed, the user's TaskList is printed in a list format.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * This method lists the tasks in tasks upon execution.
     *
     * @param tasks   the TaskList to be listed
     * @param ui      the UI that displays messages
     * @param storage the Storage is not relevant in this command
     * @return a String that displays the users' TaskList in a list format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showContainer(ui.listText(), tasks.printList());
    }
}
