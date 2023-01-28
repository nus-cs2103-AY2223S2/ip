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

    public String getTasks() {
        String str = "";
        for (int i = 1; i <= this.db.size(); i++) {
            str += (i + "." + this.db.get(i - 1) + "\n");
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * Returns all the matching tasks in string representation.
     * @param description The description to be matched.
     * @return The string representation of all matching tasks.
     */
    public String getMatchingTasks(String description) {
        String str = "";
        for (int i = 1; i <= this.db.size(); i++) {
            if (this.db.get(i - 1).getDescription().contains(description)) {
                str += (i + "." + this.db.get(i - 1) + "\n");
            }
        }
        return str.substring(0, str.length() - 1);
    }
}
