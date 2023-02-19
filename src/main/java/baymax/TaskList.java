package baymax;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = null;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
