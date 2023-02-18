package command;

import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command{
    private String desc;
    private String by;

    /**
     * Constructor for DeadlineCommand object
     * @param desc Task description
     * @param by Task deadline
     */
    public DeadlineCommand(String desc, String by){
        this.desc = desc;
        this.by = by;
    }
    /**
     * Add task to taskList and updates harddrive
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Task add message
     */
    public String execute(TaskList taskList, Storage storage, Ui ui){
        Deadline task = new Deadline(desc, by);
        taskList.addTask(task);
        storage.updateFile(taskList);
        return ui.addTaskMsg(task, taskList.size());
    };
}
