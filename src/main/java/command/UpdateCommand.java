package command;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class UpdateCommand extends Command{
    private int index;
    private String[] fields;
    private String[] values;
    public UpdateCommand(int index, String[] fields, String[] values){
        this.index = index;
        this.fields = fields;
        this.values = values;
    }
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
