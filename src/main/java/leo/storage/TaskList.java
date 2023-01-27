package leo.storage;

import leo.ui.Ui;

import java.util.List;

public class TaskList {

    private final List<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void removeTask(int num) {
        taskList.remove(num);
    }

    public Task getTask(int num) {
        return taskList.get(num);
    }

    public void display() {
        for (int i = 0; i < size(); i++) {
            Ui.displayMessage((i + 1) + ". " + getTask(i).toString());
        }
    }

}
