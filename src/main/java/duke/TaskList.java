package duke;

import java.util.ArrayList;

import duke.enums.Views;
import duke.task.Task;

/**
 * TaskList object to store the list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasksList;

    /**
     * For creating a taskList from storage
     *
     * @param tasksList as an ArrayList
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
     * @param index of the task in the ArrayList
     * @return Task found from the ArrayList
     * @throws DukeException
     */
    public Task get(int index) throws DukeException {
        try {
            return tasksList.get(index);
        } catch (IndexOutOfBoundsException e) {
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
    public ArrayList<Task> search(String... query) {
        ArrayList<Task> results = new ArrayList<Task>();
        // Inefficient O(N^2) search method. Fix this whenever
        for (Task task : this.tasksList) {
            for (String word : query) {
                if (task.getTitle().toLowerCase().contains(word.trim().toLowerCase())) {
                    results.add(task);
                }
            }
        }
        return results;
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
     * @param newTask new Task object to be added into the list
     */
    public void add(Task newTask) {
        this.tasksList.add(newTask);
    }

    /**
     * Removes the task given the index of it
     *
     * @param taskNum int index of task in the ArrayList
     */
    public void remove(int taskNum) {
        this.tasksList.remove(taskNum);
    }

    /**
     * Clears out the task list. Useful for testing
     */
    public void clear() {
        this.tasksList.clear();
    }

}
