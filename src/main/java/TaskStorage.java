import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private List<Task> taskstorage;
    // Tasks are indexed from 0 in taskstorage.
    private int ind = 0; //Number of Current Tasks

    public TaskStorage() {
        taskstorage = new ArrayList<Task>();
    }
    public void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        this.taskstorage.add(t);
        ind++;
        System.out.println("Now you have " + ind + " task(s) in the list.");
    }

    public int noTasks() {
        return ind;
    }

    public Task getTask(int i) {
        return this.taskstorage.get(i);
    }
    public void deleteTask(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        this.taskstorage.remove(t);
        ind--;
        System.out.println("Now you have " + ind + " task(s) in the list.");
    }
}
