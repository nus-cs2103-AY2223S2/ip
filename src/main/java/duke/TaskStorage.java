package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private List<Task> taskStorage;
    // Tasks are indexed from 0 in taskstorage.
    private int ind = 0; //Number of Current Tasks

    public TaskStorage() {
        taskStorage = new ArrayList<Task>();
    }
    public void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        this.taskStorage.add(t);
        this.ind++;
        System.out.println("Now you have " + this.ind + " task(s) in the list.");
    }

    public void addTaskWithoutPrinting(Task t) {
        this.taskStorage.add(t);
        this.ind++;
    }

    public int noTasks() {
        return ind;
    }

    public Task getTask(int i) {
        return this.taskStorage.get(i);
    }
    public void deleteTask(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        this.taskStorage.remove(t);
        ind--;
        System.out.println("Now you have " + ind + " task(s) in the list.");
    }

    public void listTask() {
        System.out.println("Tasks:");
        for (int i = 0; i < this.noTasks(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(this.getTask(i));
        }
    }
}
