package Storage;

import java.util.ArrayList;

import Exceptions.SelectOutOfIndexException;
import Tasks.Task;

public class TaskList {
    private ArrayList<Task> db;

    public TaskList(ArrayList<Task> db) {
        this.db = db;
    }


    public void addTask(Task task) {
        db.add(task);
    }

    public Task removeTaskByIndex(int ind) {
        if (ind >= this.db.size()) {
            throw new SelectOutOfIndexException(null);
        }
        return this.db.remove(ind);
    }

    public Task getTaskByIndex(int ind) {
        if (ind >= this.db.size()) {
            throw new SelectOutOfIndexException(null);
        }
        return this.db.get(ind);
    }

    public int getSize() {
        return this.db.size();
    }

    protected ArrayList<Task> getDb() {
        return this.db;
    }

    public boolean isEmpty() {
        return this.db.isEmpty();
    }

    public void showTasks() {
        for (int i = 1; i <= this.db.size(); i++) {
            System.out.println(i + "." + this.db.get(i-1));
        }
    }
}
