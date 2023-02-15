package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import task.Task;

/**
 * Class that stores the list of all tasks.
 */
public class TaskList {

    private final ArrayList<Task> arrOfTasks;

    /**
     * Constructs TaskList
     *
     * @param arrOfTasks Task already in the list.
     */
    public TaskList(ArrayList<Task> arrOfTasks) {
        this.arrOfTasks = arrOfTasks;
    }

    /**
     * Returns total number of tasks.
     *
     * @return Total number of tasks.
     */
    public int getTotalNumOfTasks() {
        return arrOfTasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param t New task to be added.
     */
    public void addTask(Task t) {
        arrOfTasks.add(t);
    }

    /**
     * Sets task to be done by index.
     *
     * @param index Index of task to be set as done.
     * @return Task that is set as done.
     */

    public Task markTaskDone(int index) {
        assert index >= 0 && index < arrOfTasks.size(): "index should be valid";
        Task t = arrOfTasks.get(index);
        t.markDone();
        return t;
    }

    /**
     * Sets task to be not done by index.
     *
     * @param index Index of task to be set as not done.
     * @return Task that is set as not done.
     */
    public Task markTaskNotDone(int index) {
        assert index >= 0 && index < arrOfTasks.size(): "index should be valid";
        Task t = arrOfTasks.get(index);
        t.markNotDone();
        return t;
    }

    /**
     * Deletes task by index.
     *
     * @param index Index of task to be deleted.
     * @return Task that is deleted.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < arrOfTasks.size(): "index should be valid";
        Task t = arrOfTasks.get(index);
        arrOfTasks.remove(index);
        return t;
    }

    /**
     * Returns task by index.
     *
     * @param index Index of task to be returned.
     * @return Task at index.
     */
    public Task getTaskAtIndex(int index) {
        assert index >= 0 && index < arrOfTasks.size(): "index should be valid";
        return arrOfTasks.get(index);
    }

    /**
     * Find all tasks that contain keyword.
     *
     * @param keyword Keyword that user input.
     * @return List of tasks that contains keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTask = new ArrayList<>();
        for (Task t : arrOfTasks) {
            if (t.getDescription().contains(keyword)) {
                matchingTask.add(t);
            }
        }
        return matchingTask;
    }

    /**
     * Writes details of all task to file.
     *
     * @param fw Used when writing to class.
     * @throws IOException Throws Exception when there is error writing to the file.
     */
    public void writeToFile(FileWriter fw) throws IOException {
        for (Task t : arrOfTasks) {
            fw.write(t.toText() + "\n");
        }
    }
}
