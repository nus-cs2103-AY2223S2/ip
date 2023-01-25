package task;

import java.util.ArrayList;

/**
 * Abstraction for data structure and operations used to maintain to do list.
 */
public class TaskList {
    private ArrayList<Task> records = new ArrayList<>();

    // default constructor

    public void add(Task task) {
        records.add(task);
    }

    public void delete(int x) {
        records.remove(x-1);
    }

    public String print() {
        String s = "";
        int n = this.records.size();

        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                s = s + (i + 1) + ". " + records.get(i).toString() + "\n";
            } else {
                s = s + (i + 1) + ". " + records.get(i).toString();
            }
        }

        return s;
    }

    public void mark(int x) {
        records.get(x-1).setComplete();
    }

    public void unmark(int x) {
        records.get(x-1).setIncomplete();
    }

    public int size() {
        return this.records.size();
    }
}
