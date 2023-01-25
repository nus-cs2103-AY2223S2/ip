package chattime;

import java.util.ArrayList;

import chattime.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> initialTasks) {
        tasks = initialTasks;
    }


    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeListMember(int index) {
        tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
}
