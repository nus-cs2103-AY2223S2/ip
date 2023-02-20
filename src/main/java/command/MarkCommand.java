package command;

import tasks.TaskList;
import storage.Storage;
import ui.Ui;
import tasks.Task;
public class MarkCommand extends Command{
    private int index;

    /**
     * Constructor for MarkCommand object
     * @param index Index of task to be marked
     */
    public MarkCommand(int index){
        this.index = index;
    }

    /**
     * Marks task in taskList and updates harddrive
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Mark task message
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        taskList.markTask(index);
        //update storage
        storage.updateFile(taskList);
        //print correct lines
        Task task = taskList.get(index);
        return ui.markTaskMsg(task);
    }
}
