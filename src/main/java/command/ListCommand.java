package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command{
    /**
     * Returns list of task
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Task list message
     */
    public String execute(TaskList taskList, Storage storage, Ui ui){
        return ui.listTasksMsg(taskList);
    }

}
