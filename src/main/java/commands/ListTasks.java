package commands;
import tasks.Task;
import java.util.ArrayList;

public class ListTasks implements Command{
    private ArrayList<Task> tasks;

    public ListTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public String execute(){
        StringBuilder response = new StringBuilder("Tasks:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            int current = i + 1;
            response.append(current + ". " + this.tasks.get(i) + "\n");
        }
        return response.toString();
    }
}
