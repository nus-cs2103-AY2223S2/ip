package tasks;

import java.util.ArrayList;

/**
 * A class for a Tasklist.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * constructor for TaskList which is an ArrayList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * @return size of taskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * adds the task into the TaskList
     *
     * @param task
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Mark the task at the specified index
     *
     * @param index
     */
    public void markTask(int index) {
        taskList.get(index - 1).setAsMarked();
    }

    /***
     * Unmark the task at the specified index
     * @param index
     */
    // check whether it's i -1 for this one
    public void unmarkTask(int index) {
        taskList.get(index - 1).setAsUnmarked();
    }

    /**
     * gets the task in that index
     *
     * @param index
     * @return
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * removes task in that index
     *
     * @param index
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /***
     * Checks if inserted task is a duplicate
     * true if duplicate exists
     * false if no duplicate
     * @param task
     * @return boolean
     */
    public boolean checkDuplicate(Task task) {
        for (Task t : taskList) {
            if (t.toString().equals(task.toString())) {
                return true;
            }
        }
        return false;
    }
}
