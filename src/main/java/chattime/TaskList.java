package chattime;

import chattime.task.Task;

import java.util.ArrayList;

/**
 * Represents a list object to store user's tasks.
 *
 */
public class TaskList {
    private ArrayList<Task> storeList;

    /**
     * Constructs a taskList object with ArrayList as data structure.
     *
     * @param initList Stored data loaded from storage.
     */
    public TaskList(ArrayList<Task> initList) {
        storeList = initList;
    }

    /**
     * Adds a new task to current task list.
     * @param task New task to be added.
     */
    public void addTask(Task task) {
        storeList.add(task);
    }

    /**
     * Removes index-th task object from current task list.
     *
     * @param index Input index from user, index-th task in task list to be removed.
     */
    public void removeListMember(int index) {
        storeList.remove(index - 1);
    }

    /**
     * Gets the index-th task from the task list.
     *
     * @param index Input index from user.
     * @return The index-th task in task list.
     */
    public Task getTask(int index) {
        return storeList.get(index - 1);
    }

    /**
     * Gets the current entire task list.
     *
     * @return Current task list.
     */
    public ArrayList<Task> getList() {
        return storeList;
    }
}
