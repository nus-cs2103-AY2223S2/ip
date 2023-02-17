package duke.task;

import java.util.ArrayList;


/**
 * Class for TaskList object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for users with an existing TaskList.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Constructor for new users.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task into the TaskList object.
     *
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in the TaskList object.
     *
     * @param taskNum
     */
    public void delete(int taskNum) {
        tasks.remove(taskNum);
    }

    public void set(int taskNum, Task content) {
        tasks.set(taskNum, content);
    }

    /**
     * Returns the number of the tasks in the TaskList.
     *
     * @return Number of tasks.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Returns a specific task in the TaskList.
     *
     * @param num
     * @return the Task object.
     */

    public Task getTask(int num) {
        return tasks.get(num);
    }
}

