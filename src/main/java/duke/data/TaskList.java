package duke.data;
import java.io.Serializable;
import java.util.List;

import duke.action.Task;
import duke.exception.DukeException;

/**
 * Task List class stores the list of tasks that the user has specified.
 * The class is also serializable for storing into memory.
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class TaskList implements Serializable {
    private List<Task> list;

    /**
     * Default constructor
     *
     * @param list List of tasks
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Returns the list of tasks
     *
     * @return list of tasks
     */
    public List<Task> getTasks() {
        return this.list;
    }

    /**
     * Returns task by index specified by the user
     *
     * @param index Index of task
     * @return index of task
     */
    public Task getTaskByIndex(int index) {
        return this.list.get(index);
    }

    /**
     * Adds task to the list of tasks
     *
     * @param task Task object
     */
    public void addTask(Task task) throws DukeException {
        // check if the task alr exists
        for (Task storedTask : this.list ) {
            boolean isEqual = storedTask.getDescription().equals(storedTask.getDescription());
            if (isEqual) {
                throw new DukeException(TypeOfTask.storage, 2);
            }
        }
        list.add(task);
    }

    /**
     * Removes task from the list of tasks
     *
     * @param taskIndex Index of the task specified by the user
     */
    public void removeTask(int taskIndex) {
        list.remove(taskIndex);
    }

    /**
     * Marks the task
     *
     * @param taskIndex Index of task
     */
    public void markTask(int taskIndex) {
        Task currentTask = this.list.get(taskIndex);
        currentTask.markAsDone();
    }

    /**
     * Unmarks the task
     *
     * @param taskIndex Index of task
     */
    public void unmarkTask(int taskIndex) {
        Task currentTask = this.list.get(taskIndex);
        currentTask.unmarkAsDone();
    }

    /**
     * Returns the size of the list of tasks
     *
     * @return size of list
     */
    public int getSize() {
        return this.list.size();
    }
}
