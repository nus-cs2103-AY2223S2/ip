package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;
import tasks.Task;
public class UnmarkCommand extends Command{
    private int index;
    public UnmarkCommand(int index){
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui){
        taskList.unmarkTask(index);
        storage.updateFile(taskList);
        Task task = taskList.get(index);
        System.out.println(ui.unmarkTaskMsg(task));
    }
}
