package Features;

import java.util.ArrayList;

import Tasks.Task;

public class TaskList {

    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public void clone(TaskList cloneFrom) {
        TaskList tempList = new TaskList();
        for (int i = 0; i < cloneFrom.size(); i++) {
            tempList.add(cloneFrom.get(i));
        }
        while (this.list.size() > 0) {
            this.list.remove(0);
        }
        for (int i = 0; i < tempList.size(); i++) {
            this.list.add(tempList.get(i));
        }
    }
}
