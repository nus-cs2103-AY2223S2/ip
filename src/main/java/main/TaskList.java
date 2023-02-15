package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import task.Task;

/**
 * Class that stores the list of all tasks.
 */
public class TaskList {

    private final ArrayList<Task> arrOfTask;

    /**
     * Constructs TaskList
     *
     * @param arrOfTask Task already in the list.
     */
    public TaskList(ArrayList<Task> arrOfTask) {
        this.arrOfTask = arrOfTask;
    }

    /**
     * Returns total number of tasks.
     *
     * @return Total number of tasks.
     */
    public int getTotalNumberOfTask() {
        return arrOfTask.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param t New task to be added.
     */
    public void addTask(Task t) {
        arrOfTask.add(t);
    }

    /**
     * Sets task to be done by index.
     *
     * @param index Index of task to be set as done.
     * @return Task that is set as done.
     */
    public Task taskDone(int index) {
        assert index > 0 && index < arrOfTask.size(): "index should be valid";
        Task t = arrOfTask.get(index);
        t.taskDone();
        return t;
    }

    /**
     * Sets task to be not done by index.
     *
     * @param index Index of task to be set as not done.
     * @return Task that is set as not done.
     */
    public Task taskNotDone(int index) {
        assert index > 0 && index < arrOfTask.size(): "index should be valid";
        Task t = arrOfTask.get(index);
        t.taskNotDone();
        return t;
    }

    /**
     * Deletes task by index.
     *
     * @param index Index of task to be deleted.
     * @return Task that is deleted.
     */
    public Task deleteTask(int index) {
        assert index > 0 && index < arrOfTask.size(): "index should be valid";
        Task t = arrOfTask.get(index);
        arrOfTask.remove(index);
        return t;
    }

    /**
     * Returns task by index.
     *
     * @param index Index of task to be returned.
     * @return Task at index.
     */
    public Task getTaskAtIndex(int index) {
        assert index > 0 && index < arrOfTask.size(): "index should be valid";
        return arrOfTask.get(index);
    }

    /**
     * Find all tasks that contain keyword.
     *
     * @param keyword Keyword that user input.
     * @return List of tasks that contains keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchedTask = new ArrayList<>();
        for (Task t : arrOfTask) {
            if (t.getNameOfTask().contains(keyword)) {
                matchedTask.add(t);
            }
        }
        return matchedTask;
    }

    /**
     * Writes details of all task to file.
     *
     * @param fw Used when writing to class.
     * @throws IOException Throws Exception when there is error writing to the file.
     */
    public void writeToFile(FileWriter fw) throws IOException {
        for (Task t : arrOfTask) {
            fw.write(t.toText() + "\n");
        }
    }
}
