package lele.task;

import lele.exception.NoPreviousCommandException;

import java.util.ArrayList;

/**
 * The place to store all the user's task, and perform
 * operations on.
 *
 */
public class TaskList {
    protected ArrayList<Task> list; // Always contain the most updated list
    private ArrayList<ArrayList<Task>> cache;

    /**
     * Important note: Cache the list FIRST before committing to any
     * operations on this.list. THis is so that the original cache
     * does not get overridden by the operations, then caching that
     * wrong version!
     *
     */
    public void cacheList() {
        ArrayList<Task> toBeStored = new ArrayList<>();
        for (Task task: this.list) {
            toBeStored.add(task);
        }
        this.list = toBeStored;
        this.cache.add(toBeStored);
    }

    /**
     * Restores previous cache by index number of version.
     *
     * @param index number of versions to backtrack by.
     * @throws NoPreviousCommandException Thrown if exceeded version
     */
    public void restorePreviousList(int index) throws NoPreviousCommandException {
        // Don't do anything if number of versions to undo is 0
        if (index <= 0) {
            return;
        }
        // Need to add 1 to account for the original list at index 0
        int versionToBeRestoredTo = this.cache.size() - index - 1;
        if (versionToBeRestoredTo < 0) {
            throw new NoPreviousCommandException("Not possible, that's too far back into history!");
        }
        this.list = this.cache.get(versionToBeRestoredTo);
        /*  Clears all versions in the cache after the calculated index.
            If cache size is equals to 1, it'll mean that the cache has
            only 1 item, which implies that it is at its original version.
         */
        if (this.cache.size() > 1) {
            this.cache.subList(versionToBeRestoredTo + 1, this.cache.size()).clear();
        }
    }
    /**
     * Loads the task list provided from storage.
     *
     * @param storageList Loaded with tasks from storage
     */
    public TaskList(ArrayList<Task> storageList) {
        this.list = storageList;
        this.cache = new ArrayList<>();
        // Adds the original task list in the storage to the cache
        this.cache.add(storageList);
    }

    /**
     * If the user does not have existing date, a new task list is created.
     * Thus, this constructor will be used instead of the previous one.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.cache = new ArrayList<>();
        this.cache.add(new ArrayList<>());
    }

    /**
     * Prints the current list of tasks.
     * @return Output to user.
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            sb.append((i + 1) + "." + this.list.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Finds the tasks where their description matches
     * the regex of the query.
     *
     * @param regex The description to find.
     * @return Output tasks to user.
     */
    public String findTask(String regex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (this.list.get(i).getDescription().contains(regex)) {
                sb.append((i + 1) + "." + task + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * The size of the task list, to be used by
     * other internal methods for calculations.
     *
     * @return The size of the current task list.
     */
    public int listSize() {
        return this.list.size();
    }

    /**
     * A specific task to be returned from a query.
     *
     * @param index The location of the task in the array list.
     * @return The task queried.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the list of task.
     *
     * @return List of task.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Marks the specific task specified by the query.
     *
     * @param index The location of the task in the array list
     */
    public void markStatus(int index) {
        cacheList();
        list.get(index - 1).markStatus(true);
    }

    /**
     * Unmarks the specific task specified by the query.
     *
     * @param index The location of the task in the array list.
     */
    public void unMarkStatus(int index) {
        cacheList();
        list.get(index - 1).markStatus(false);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        cacheList();
        this.list.add(task);
    }

    /**
     * Deletes a task to the task list.
     *
     * @param inputIndex The location of the task in the array list.
     */
    public Task deleteTask(int inputIndex) {
        // Use an exception instead of assert to handle the code below
        // assert this.listSize() >= inputIndex : "Index parameter queried should be within the task size";
        cacheList();
        Task task = this.list.get(inputIndex - 1);
        this.list.remove(inputIndex - 1);
        return task;
    }

}
