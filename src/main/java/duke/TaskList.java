package duke;

import java.util.ArrayList;
import java.util.Iterator;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> taskList;
    private int count;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void list() {
        int curr = 1;
        Iterator<Task> iter = this.taskList.iterator();
        while (iter.hasNext()) {
            System.out.println(curr + " " + iter.next());
            curr++;
        }
    }

    public void addTask(Task t) {
        this.taskList.add(t);
        count++;
    }

    
    public void deleteTask(int i) {
        Task t = this.taskList.remove(i);
        count--;
        System.out.println("Deleted task:\n  " + t + "\nNumber of tasks: " + count);
    }

    public void markTask(int i) {
        Task t = this.taskList.get(i);
        t.mark();
        System.out.println("Task has been marked as done:\n " + t);
    }

    public void unmarkTask(int i) {
        Task t = this.taskList.get(i);
        t.unmark();
        System.out.println("Task has been marked as not done:\n " + t);
    }

    public int size() {
        return count;
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }
}
