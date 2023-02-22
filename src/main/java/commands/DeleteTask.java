package commands;
import tasks.Task;
import java.util.ArrayList;

public class DeleteTask implements Command {
    private int taskIndex;
    private ArrayList<Task> taskList;

    public DeleteTask(int taskIndex, ArrayList<Task> taskList) {
        this.taskIndex = taskIndex;
        this.taskList = taskList;
    }

    public String execute() {
        try {
            Task removed = taskList.remove(taskIndex - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Removed ";
    }
}
