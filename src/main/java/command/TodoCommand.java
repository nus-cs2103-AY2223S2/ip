package command;

import storage.Storage;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

public class TodoCommand extends Command{
    private String desc;
    public TodoCommand(String desc){
        this.desc = desc;
    }
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        storage.updateFile(taskList);
        return ui.addTaskMsg(todo, taskList.size());
    }
}
