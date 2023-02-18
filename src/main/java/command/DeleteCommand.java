package command;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command{
    private int index;

    /**
     * Constructor for DeleteCommand object
     * @param index Index of task to be deleted
     */
    public DeleteCommand(int index){
        assert index > -1: "Index should not be negative";
        this.index = index;
    }
    /**
     * Deletes task from taskList and updates harddrive
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Task delete message
     */
    public String execute(TaskList taskList, Storage storage, Ui ui){
        Task task = taskList.get(index);
        taskList.deleteTask(index);
        storage.updateFile(taskList);
        return ui.deleteTaskMsg(task, taskList.size());
    };
}
