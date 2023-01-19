package commands;
import tasks.Task;
import java.util.ArrayList;

public class AddTask implements Command{
    private Task task;
    private ArrayList<Task> taskList;

    public AddTask(Task task, ArrayList<Task> taskList) {
        this.task = task;
        this.taskList = taskList;
    }
    public String execute(){
        this.taskList.add(task);
        return "added: " + this.task.getDescription();
    }
}
