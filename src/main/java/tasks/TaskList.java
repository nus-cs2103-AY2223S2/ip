package tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void print() {
        for (int i = 0; i < list.size(); i++){
            Task task = list.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task get(int idx) {
        return list.get(idx);
    }

    public void delete(int idx) {
        list.remove(idx);
    }

    public int getSize() {
        return list.size();
    }

    public void set(int idx, Task task) {
        list.set(idx, task);
    }
}
