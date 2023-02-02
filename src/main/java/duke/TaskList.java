package duke;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeInvalidArgumentException;
import duke.task.Task;

/**
 * TaskList has the basic functionalities of a checklist.
 * A checklist keeps track of everyday tasks or events.
 */
public class TaskList {
    /** Backing array of Tasks */
    private List<Task> userTasks;
    /** Underlying storage to load and save Tasks */
    private Storage storage;

    /**
     * Constructs a TaskList and initialises the underlying backing store if it
     * does not exist. Otherwise, load data from the backing store.
     */
    public TaskList() {
        this.storage = new Storage();
        this.userTasks = storage.loadData();
    }

    /**
     * Marks the task with the specific index as done.
     * @param index Index of task in the list.
     * @throws DukeInvalidArgumentException If index is out of bounds.
     */
    public void markTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("There are only %d tasks. There is no task "
                            + "with index %d", this.userTasks.size(), index)
            );
        }
        this.userTasks.get(index).setIsDone(true);
    }

    /**
     * Marks the task with the specific index as not done.
     * @param index Index of task in the list.
     * @throws DukeInvalidArgumentException If index is out of bounds.
     */
    public void unmarkTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("There are only %d tasks. There is no task "
                            + "with index %d", this.userTasks.size(), index)
            );
        }
        this.userTasks.get(index).setIsDone(false);
    }

    /**
     * Add a Task to TaskList.
     * @param t Task to add.
     */
    public void addTask(Task t) {
        this.userTasks.add(t);
    }

    /**
     * Pops and returns the task with the specific index in the list.
     * @param index Index of task in the list.
     * @return Task that is removed.
     * @throws DukeInvalidArgumentException If index is out of bounds.
     */
    public Task popTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("\tThere are only %d tasks. There is no task "
                            + "with index %d\n", this.userTasks.size(), index)
            );
        }
        return this.userTasks.remove(index);
    }

    /**
     * Returns a String representation of all Task objects in the list.
     * @return String representation of all tasks stored in TaskList.
     */
    public String getAllTaskString() {
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (Task s: this.userTasks) {
            result.append(String.format("%d.%s\n", i, s));
            i++;
        }
        return result.toString().replaceAll("\\n$", "");
    }

    public List<Task> getTaskByKeyword(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task s: this.userTasks) {
            if (s.getTitle().contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Returns the Task at the specified index.
     * @param index Index of task in the list.
     * @return Task that has the index in the list.
     */
    public Task getTask(int index) {
        return this.userTasks.get(index);
    }

    /**
     * Returns the number of tasks in TaskList.
     * @return Number of tasks in TaskList.
     */
    public int getNoOfTasks() {
        return this.userTasks.size();
    }

    /**
     * Saves the underlying data to duke.Storage.
     */
    public void close() {
        this.storage.saveData(this.userTasks);
    }

}
