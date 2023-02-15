package duke.tasklist;

import java.util.*;

import duke.exceptions.DukeInvalidArgumentException;
import duke.storage.Storage;
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
                    String.format("There are only %d tasks, so you can't get a task "
                            + "with index %d", this.userTasks.size(), index + 1)
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
                    String.format("There are only %d tasks, so you can't get a task "
                            + "with index %d", this.userTasks.size(), index + 1)
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
                    String.format("There are only %d tasks, so you can't get a task "
                            + "with index %d\n", this.userTasks.size(), index + 1)
            );
        }
        return this.userTasks.remove(index);
    }

    /**
     * Pops and returns the list of tasks with the specific indexes in the list.
     * @param indexes Indexes of the tasks to pop in the list.
     * @return List of Tasks that are removed.
     * @throws DukeInvalidArgumentException If any index is out of bounds.
     */
    public List<Task> popMultipleTasks(List<Integer> indexes) throws DukeInvalidArgumentException {
        for (Integer index: indexes) {
            if (index < 0 || index > this.userTasks.size() - 1) {
                throw new DukeInvalidArgumentException(
                        String.format("There are only %d tasks, so you can't get a task "
                                + "with index %d\n", this.userTasks.size(), index + 1)
                );
            }
        }
        Set<Integer> uniqueIndexes = new HashSet<>(indexes);
        if (uniqueIndexes.size() < indexes.size()) {
            throw new DukeInvalidArgumentException(
                    String.format("All indexes must be unique.")
            );
        }

        indexes.sort(Collections.reverseOrder());
        List<Task> result = new ArrayList<>();
        for (int index: indexes) {
            result.add(this.userTasks.remove(index));
        }
        return result;
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

    /**
     * Returns a list of all the tasks whose name contains the keyword.
     * @param keyword Keyword to find tasks.
     * @return List of tasks whose name contains the keyword.
     */
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
    public void save() {
        this.storage.saveData(this.userTasks);
    }

}
