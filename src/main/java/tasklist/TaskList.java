package tasklist;

import java.util.*;
import storage.Storage;
import tasktypes.Task;

public class TaskList {
    private ArrayList<Task> taskStorage;
    private int numTasks;

    public TaskList() {
        taskStorage = new ArrayList<>();
        this.numTasks = 0;
    }

    public void loadTask(Task task) {
        this.taskStorage.add(task);
        numTasks++;
    }

    public void addTask(Task task) {
        this.taskStorage.add(task);
        numTasks++;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void deleteTask(int toDelete) {
        numTasks--;
        Task deleted = taskStorage.remove(toDelete - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void markTask(int mark) {
        Task marked = taskStorage.get(mark - 1);
        marked.markDone();
        System.out.println("Nice! I've marked this task as done:\n" + marked);
    }

    public void unmarkTask(int unmark) {
        Task unmarked = taskStorage.get(unmark - 1);
        unmarked.markUndone();
        System.out.println("OK, I've marked this task as undone:\n" + unmarked);
    }

    public void printTasks() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskStorage) {
            String output = String.format("%d.%s", count++, task.toString());
            System.out.println(output);
        }
    }

    public ArrayList<Task> getTasks() {
        return taskStorage;
    }

    public int getNumTasks() {
        return numTasks;
    }

}
