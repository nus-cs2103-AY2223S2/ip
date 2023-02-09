package command;

import tasks.TaskList;
import storage.Storage;
import ui.Ui;
import tasks.Task;
public class MarkCommand extends Command{
    private int index;
    public MarkCommand(int index){
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui){
        taskList.markTask(index);
        //update storage
        storage.updateFile(taskList);
        //print correct lines
        Task task = taskList.get(index);
        System.out.println(ui.markTaskMsg(task));
    }
}
