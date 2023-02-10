import java.io.*;
import java.util.*;
public class TaskList {
    private static ArrayList<Task> list;
    private static Storage storage;
    public TaskList() {
        this.list = new ArrayList<>();
        File save = new File("./duke.txt");
        try { 
        save.createNewFile();
        } catch (IOException e) {
        //we will never call this if we did not need to create a new file (check main)
        }
        this.storage = new Storage("./duke.txt");
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    private Storage getStorage() {
        return this.storage; 
    } 
    public void showList() {
        int j = 0;
        for (Task i: list) {
            j++;
            System.out.println(String.valueOf(j) + ". " + i);
        }
    }
    public void markTask(int i, boolean b) {
        int index = i - 1;
        list.get(index).markTask(b);
        System.out.println("Marked/Unmarked the task, task is in the state:\n");
        System.out.print("  " + list.get(index));
    }
    public void addList(Task task) {
        list.add(task);
        System.out.println("added: " + task.getDescription());
        System.out.print("You have: " + list.size() + " task(s)\n");
    }
    public void deleteTask(int i) {
        int index = i - 1;
        System.out.println("removed: " + list.get(index).toString());
        System.out.print("You have: " + (list.size() - 1) + " task(s)\n");
        list.remove(index);
    }
}
