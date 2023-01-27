package seedu;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    public void addToTaskList(Task task) {

        taskList.add(task);
    }

    public void markTask(int index) {
        taskList.get(index).markDone();
    }

    public void unmarkTask(int index) {
        taskList.get(index).markUnDone();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }
}
