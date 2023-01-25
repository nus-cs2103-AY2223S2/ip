import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> userTasks;
    public TaskList(ArrayList<Task> tasks){
        this.userTasks = tasks;
    }

    public ArrayList<Task> getUserTasks(){
        return userTasks;
    }
}
