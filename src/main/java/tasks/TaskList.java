package tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    public void markTask(int index) {
        this.tasks.get(index - 1).markAsDone();
    }

    public void unmarkTask(int index) {
        this.tasks.get(index - 1).markAsUndone();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    public ArrayList<Task> getArrayListCopy() {
        return new ArrayList<>(this.tasks);
    }
}
