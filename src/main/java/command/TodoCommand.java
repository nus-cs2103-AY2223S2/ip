package command;

import storage.Storage;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

public class TodoCommand extends Command{
    private String desc;

    /**
     * Constructor for TodoCommand object
     * @param desc Task description
     */
    public TodoCommand(String desc){
        this.desc = desc;
    }

    /**
     * Adds task to taskList and updates harddrive
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Task add message
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        storage.updateFile(taskList);
        return ui.addTaskMsg(todo, taskList.size());
    }
}
