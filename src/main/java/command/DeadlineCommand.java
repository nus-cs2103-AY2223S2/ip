package command;

import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command{
    private String desc;
    private String by;
    public DeadlineCommand(String desc, String by){
        this.desc = desc;
        this.by = by;
    }
    public String execute(TaskList taskList, Storage storage, Ui ui){
        Deadline task = new Deadline(desc, by);
        taskList.addTask(task);
        storage.updateFile(taskList);
        return ui.addTaskMsg(task, taskList.size());
    };
}
