package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(int size) {
        list = new ArrayList<Task>(size);
    }
    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public int getSize() {
        return list.size();
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public Task removeTask(int index) {
        return list.remove(index);
    }

    public TaskList findTask(String str) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).getName().contains(str)) {
                result.addTask(this.getTask(i));
            }
        }
        return result;
    }
}
