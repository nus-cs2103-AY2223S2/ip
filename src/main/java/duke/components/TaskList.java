package duke.components;

import java.io.Serializable;
import java.util.ArrayList;

import duke.tasks.Task;
/***
 * This is the TaskList class for Duke, the CLI task manager.
 * This class is used to store tasks, as well as manipulate them
 * as needed. An object of this class will be written into storage
 * at the end of the user session and retrieved for their next session.
 */

public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;

    /**
     * Returns a new TaskList with no tasks stored.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     * @param taskToAdd Task to be added.
     */
    public void addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    /**
     * Returns the number of tasks currently stored.
     * @return int of the number of tasks stored.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes Task at provided index of the TaskList.
     * @param indexOfTask int of index which the Task resides.
     */
    public void deleteTask(int indexOfTask) {
        tasks.remove(indexOfTask - 1);
    }

    /**
     * Marks Task at provided index of the TaskList.
     * @param markIndex int of index which the Task resides.
     */
    public void mark(int markIndex) {
        Task currTask = tasks.get(markIndex - 1);
        currTask.markDone();
    }

    /**
     * Returns the Task at provided index of the TaskList.
     * @param index int of index which the Task resides.
     * @return Task at the index provided.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Unmarks Task at provided index of the TaskList.
     * @param unmarkIndex int of index which the Task resides.
     */
    public void unmark(int unmarkIndex) {
        Task currTask = tasks.get(unmarkIndex - 1);
        currTask.unmark();
    }

}
