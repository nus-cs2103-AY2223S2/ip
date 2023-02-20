package command;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class UpdateCommand extends Command{
    private int index;
    private String[] fields;
    private String[] values;

    /**
     * Constructor for UpdateCommand object
     * @param index Index for task to be updated
     * @param fields Fields to update in task
     * @param values Values to update fields with
     */
    public UpdateCommand(int index, String[] fields, String[] values){
        this.index = index;
        this.fields = fields;
        this.values = values;
    }
    /**
     * Updates task to taskList and updates harddrive
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Update task message
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        //update existing task
        Task task = taskList.get(index);
        task.updateFields(fields, values);
        //update storage
        storage.updateFile(taskList);
        //return print msg
        return ui.updateMsg(task);
    }
}
