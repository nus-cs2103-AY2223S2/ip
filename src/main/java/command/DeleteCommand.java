package command;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index){
        this.index = index;
    }
    public void execute(TaskList taskList, Storage storage, Ui ui){
        Task task = taskList.get(index);
        taskList.deleteTask(index);
        storage.updateFile(taskList);
        System.out.println(ui.deleteTaskMsg(task, taskList.getSize()));
    };
}
