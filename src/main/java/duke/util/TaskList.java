package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> retrieveList() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public void markTask(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        this.taskList.get(index).markAsUndone();
    }
}
