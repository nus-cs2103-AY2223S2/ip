package duke.tasklist;

import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the list of tasks that the user has.
 */
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

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param t The task to be added.
     * @param type The type of the task added.
     */
    public void addTask(Task t, TaskTypes type) {
        tasks.add(t);
        this.storage.addTask(t, type);
    }

    /**
     * Deletes a task from the list of tasks based on the
     * passed index.
     *
     * @param index The index of the task in the list to be
     *              deleted.
     * @return The task that was deleted.
     * @throws DukeException If the passed index is larger than
     * the size of the list.
     */
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

    /**
     * Returns the task in the list with the passed index.
     *
     * @param index The index of the desired task in the list.
     * @return The task with the given index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks the task with given index as completed.
     *
     * @param index Index of the task to be marked.
     * @return True if the task was successfully marked, false
     * otherwise.
     * @throws DukeException If given index is larger than the
     * size of the list.
     */
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

    /**
     * Unmarks the task with the given index such that it is
     * uncompleted.
     *
     * @param index The index of the task to be unmarked.
     * @return True if the task was successfully unmarked, false
     * otherwise.
     * @throws DukeException If given index is larger than the
     * size of the list.
     */
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

    /**
     * Returns a list of tasks with a description matching
     * the input description.
     *
     * @param word
     * @return
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> result = this.tasks.stream()
                .filter(t -> t.getName().contains(word))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
}
