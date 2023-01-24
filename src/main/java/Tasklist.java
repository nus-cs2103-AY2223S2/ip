import exceptions.DukeException;

import java.util.ArrayList;

public class Tasklist {

    private ArrayList<Task> tasks;
    private Storage storage;

    public Tasklist(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = storage.load();
    }

    public int size() {
        return this.tasks.size();
    }

    public void addTask(Task t, TaskTypes type) {
        tasks.add(t);
        this.storage.addTask(t, type);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index >= this.tasks.size()) {
            throw new DukeException("Invalid task number provided. "
                    + "Given task number is " + String.valueOf(index + 1)
                    + " but there are only " + this.tasks.size()
                    + " task(s) in the list");
        }
        this.storage.deleteTask(index);
        return this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public boolean mark(int index) throws DukeException {
        if (index >= this.tasks.size()) {
            throw new DukeException("Invalid task number provided. "
                    + "Given task number is " + String.valueOf(index + 1)
                    + " but there are only " + this.tasks.size()
                    + " task(s) in the list");
        }
        if (this.tasks.get(index).markAsDone()) {
            this.storage.mark(index);
            return true;
        }
        return false;
    }

    public boolean unmark(int index) throws DukeException {
        if (index >= this.tasks.size()) {
            throw new DukeException("Invalid task number provided. "
                    + "Given task number is " + String.valueOf(index + 1)
                    + " but there are only " + this.tasks.size()
                    + " task(s) in the list");
        }
        if (this.tasks.get(index).markAsUndone()) {
            this.storage.unmark(index);
            return true;
        }
        return false;
    }

    public void viewList() {
        if (this.tasks.isEmpty()) {
            System.out.println("\t You currently have no tasks.");
        } else {
            System.out.println("\t Here is a list of your tasks:");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println("\t " + String.valueOf(i+1) + "." +  this.tasks.get(i));
            }
        }

    }
}
