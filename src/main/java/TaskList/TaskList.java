package TaskList;

import Task.Task;
import java.util.ArrayList;
import Ui.Ui;

/**
 * Class to encapsulate the list of tasks and its corresponding methods.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Constructor that uses given ArrayList to create a TaskList object.
     *
     * @param list ArrayList to be modelled after.
     */
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    /**
     * Constructor for a new TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns Task corresponding to given index.
     * 0-indexed.
     *
     * @param index index to get the corresponding Task.
     * @return
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the whole list that this object encapsulates.
     *
     * @return ArrayList that is encapsulated by this TaskList.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns the current size of the ArrayList that this object encapsulates.
     * Size corresponds to number of objects.
     *
     * @return Number of Tasks in the ArrayList that this TaskList encapsulates.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Adds given Task to the ArrayList that this TaskList encapsulates.
     *
     * @param task given Task to add to ArrayList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        // Call ui added.
        Ui.showAddedMessage(task);
    }

    /**
     * Removes Task from the list in the given index.
     *
     * @param index position of Task to be removed.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
        // Call ui removed
        Ui.showRemovedMessage(this.get(index));
    }

    /**
     * Marks Task as done from the list in the given index.
     *
     * @param index position of Task to be marked as done.
     */
    public void markDone(int index) {
        this.taskList.get(index).markDone();
        // Call ui marked
        Ui.showMarkedMessage(this.get(index));
    }

    /**
     * Marks Task as undone from the list in the given index.
     *
     * @param index position of Task to be marked as undone.
     */
    public void markUndone(int index) {
        this.taskList.get(index).markUndone();
        // Call UI unmarked
        Ui.showUnmarkedMessage(this.get(index));
    }

}
