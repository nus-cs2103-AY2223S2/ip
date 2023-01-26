package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<Task>();
    }

    public boolean add(Task task) {
        return tasks.add(task);
    }

    // Note: need to be int and not Integer
    // or else remove(Object o) is used (wrong)
    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    public void removeAllTask() {
        tasks.clear();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }
    public int size() {
        return tasks.size();
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    //TODO: hacked, fixeme
    public ArrayList<Task> getInner() {
        return tasks;
    }
}
