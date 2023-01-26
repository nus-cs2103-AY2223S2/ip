package duke.tasklist;

import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tasklist {

    private ArrayList<Task> tasks;
    private Storage storage;

    public Tasklist(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    public ArrayList<Task> getList() {
        return this.tasks;
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

    public ArrayList<Task> find(String word) {
        ArrayList<Task> result =
                this.tasks.stream()
                        .filter(t -> t.getName().contains(word))
                        .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
}
