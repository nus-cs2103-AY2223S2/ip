package duke;
import duke.task.*;
import java.io.*;
import java.util.*;

/**
 * stores and handles edits to current session task list 
 */
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
    /**
     * Marks task according to boolean
     *
     * @param i task to remove
     * @param b boolean whether it is to be marked or un mark
     */
    public void markTask(int i, boolean b) {
        int index = i - 1;
        list.get(index).markTask(b);
        System.out.println("Marked/Unmarked the task, task is in the state:\n");
        System.out.print("  " + list.get(index));
    }
    /**
     * add task to list
     *
     * @param task task to be added to list
     */
    public void addList(Task task) {
        list.add(task);
        System.out.println("added: " + task.getDescription());
        System.out.print("You have: " + list.size() + " task(s)\n");
    }
    /**
     * remeove task to list
     *
     * @param i task to be added to list
     */
    public void deleteTask(int i) {
        int index = i - 1;
        System.out.println("removed: " + list.get(index).toString());
        System.out.print("You have: " + (list.size() - 1) + " task(s)\n");
        list.remove(index);
    }
    public String find(String word) {
    StringBuilder display = new StringBuilder();
    for (int i = 0; i < this.list.size(); i++) {
        Task curr = this.list.get(i);
        if (curr.toString().toLowerCase().contains(word.toLowerCase())) {
            display.append((curr) + ". " + curr.getStatusIcon() + "\n");
        }
    }
    return display.toString();
    };
}
