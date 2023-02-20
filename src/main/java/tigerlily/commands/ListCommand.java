package tigerlily.commands;

import tigerlily.util.Storage;
import tigerlily.util.Ui;

import tigerlily.tasks.TaskList;

public class ListCommand implements Command {
    /**
     * Runs process to display all Tasks in the TaskList.
     *
     * @param taskList the TaskList used during this session
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used to update the save file
     * @return the contents of the TaskList
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showMessage(taskList.printList());
    }
}