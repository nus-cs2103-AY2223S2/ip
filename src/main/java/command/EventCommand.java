package command;

import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

public class EventCommand extends Command{
    private String desc;
    private String from;
    private String to;
    public EventCommand(String desc, String from, String to){
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui){
        Event task = new Event(desc, from, to);
        taskList.addTask(task);
        storage.updateFile(taskList);
        System.out.println(ui.addTaskMsg(task, taskList.size()));
    };
}
