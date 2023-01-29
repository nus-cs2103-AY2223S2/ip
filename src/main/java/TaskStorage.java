import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static ArrayList<Task> taskstorage;
    // Tasks are indexed from 0 in taskstorage.
    private static int ind = 0; //Number of Current Tasks

    public TaskStorage() {
        taskstorage = new ArrayList<Task>();
    }
    public static void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        taskstorage.add(t);
        ind++;
        System.out.println("Now you have " + ind + " task(s) in the list.");
    }

    public int noTasks() {
        return ind;
    }

    public Task getTask(int i) {
        return taskstorage.get(i);
    }
    public static void deleteTask(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        taskstorage.remove(t);
        ind--;
        System.out.println("Now you have " + ind + " task(s) in the list.");
    }
}
