package command;

import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

public class EventCommand extends Command{
    private String desc;
    private String from;
    private String to;

    /**
     * Constructor for EventCommand object
     * @param desc Task description
     * @param from Task start date
     * @param to Task end date
     */
    public EventCommand(String desc, String from, String to){
        this.desc = desc;
        this.from = from;
        this.to = to;
    }
    /**
     * Adds task to taskList and updates harddrive
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Task add message
     */
    public String execute(TaskList taskList, Storage storage, Ui ui){
        Event task = new Event(desc, from, to);
        taskList.addTask(task);
        storage.updateFile(taskList);
        return ui.addTaskMsg(task, taskList.size());
    };
}
