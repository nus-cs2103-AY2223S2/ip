package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.tasks.TaskList;

public class ListCommand implements Command {
    /**
     * Runs process to display all Tasks in the TaskList.
     *
     * @param taskList the TaskList used during this session
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used to update the save file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage(taskList.printList());
    }
}