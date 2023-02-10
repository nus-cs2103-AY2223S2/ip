package tasklist;

import java.util.ArrayList;

import task.Task;

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
    }

    /**
     * Removes Task from the list in the given index.
     *
     * @param index position of Task to be removed.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);

    }

    /**
     * Marks Task as done from the list in the given index.
     *
     * @param index position of Task to be marked as done.
     */
    public void markDone(int index) {
        this.taskList.get(index).markDone();
    }

    /**
     * Marks Task as undone from the list in the given index.
     *
     * @param index position of Task to be marked as undone.
     */
    public void markUndone(int index) {
        this.taskList.get(index).markUndone();
    }

    /**
     * Given a string, find all tasks in the ArrayList in this, and returns them in an ArrayList.
     * @param find String to match and find.
     * @return ArrayList of Tasks that match the find.
     */
    public ArrayList<Task> findArray(String find) {
        ArrayList<Task> list = new ArrayList<Task>();
        for (int i = 0; i < this.size(); i++) {
            String temp = this.get(i).getCommand();
            if (temp.contains(find)) {
                list.add(this.get(i));
            }
        }
        return list;
    }
}
