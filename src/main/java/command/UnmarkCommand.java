package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;
import tasks.Task;
public class UnmarkCommand extends Command{
    private int index;
    /**
     * Constructor for MarkCommand object
     * @param index Index of task to be unmarked
     */
    public UnmarkCommand(int index){
        this.index = index;
    }

    /**
     * Adds task to taskList and updates harddrive
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Unmark task message
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        taskList.unmarkTask(index);
        storage.updateFile(taskList);
        Task task = taskList.get(index);
        return ui.unmarkTaskMsg(task);
    }
}
