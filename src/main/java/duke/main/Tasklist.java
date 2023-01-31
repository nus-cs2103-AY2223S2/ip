package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class Tasklist {
    private final ArrayList<Task> tasks;
    public Tasklist() {
        this.tasks = new ArrayList<>();
    }
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }
    public void markDone(int taskNum) {
        tasks.get(taskNum).markDone();
    }
    public void markUndone(int taskNum) {
        tasks.get(taskNum).markUndone();
    }
}
