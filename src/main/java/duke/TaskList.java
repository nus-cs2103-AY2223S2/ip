package duke;

import java.util.ArrayList;

import duke.task.Task;
import duke.enums.Views;

/**
 * TaskList object to store the list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasksList;

    /**
     * For creating a taskList from storage
     *
     * @param tasksList
     * @throws DukeException
     */
    TaskList(ArrayList<Task> tasksList) throws DukeException {
        this.tasksList = tasksList;
    }

    /**
     * Creates a empty task list
     */
    TaskList() {
        this.tasksList = new ArrayList<Task>(100);
    }

    /**
     * Gets the Task at the index specified
     *
     * @param index
     * @return Task
     * @throws DukeException
     */
    public Task get(int index) throws DukeException {
        try {
            return tasksList.get(index);
        } catch (Exception e) {
            throw new DukeException(Views.OUT_RANGE_ERR_STRING.eng());
        }
    }

    /**
     * Gets the array list for the list of items
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return tasksList;
    }

    /**
     * Searches and returns an arraylist for results
     *
     * @return ArrayList of found task
     */
    public ArrayList<Task> search(String query) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task task : this.tasksList) {
            if (task.getTitle().toLowerCase().contains(query.trim().toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * @return int size of the taskList
     */
    public int size() {
        return tasksList.size();
    }

    /**
     * Adds a new task into the list
     *
     * @param newTask
     */
    public void add(Task newTask) {
        this.tasksList.add(newTask);
    }

    /**
     * Removes the task given the index of it
     *
     * @param taskNo
     */
    public void remove(int taskNo) {
        this.tasksList.remove(taskNo);
    }

    /**
     * Clears out the task list. Useful for testing
     */
    public void clear() {
        this.tasksList.clear();
    }

}
