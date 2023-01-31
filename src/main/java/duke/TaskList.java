package duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> initList) {
        this.list = initList;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    public int getSize() {
        return list.size();
    }

    public List<Task> getTaskList() {
        return list;
    }
}
