package duke;
import duke.task.*;
import java.io.*;
import java.util.*;
public class TaskList {
    private static ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public ArrayList<Task> getList() {
        return this.list; 
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
