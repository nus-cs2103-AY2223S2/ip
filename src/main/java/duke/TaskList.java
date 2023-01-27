package duke;

import java.util.ArrayList;

/**
 * Encapsulates a container for the task list.
 *
 * @author Sean Chin Jun Kai.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for storing current TaskList.
     *
     * @param taskList list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Constructor for initialising new TaskList.
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to the TaskList.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves a task from the TaskList based on given ID.
     *
     * @param taskNumber ID of the task in the TaskList.
     * @return chosen Task.
     * @throws DukeException if given ID does not exist in TaskList.
     */
    public Task getTask(int taskNumber) throws DukeException {
        try {
            Task chosenTask = tasks.get(taskNumber - 1);
            return chosenTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
    }

    /**
     * Removes task from TaskList.
     *
     * @param chosen task to be removed.
     * @return deleted Task.
     * @throws DukeException if Task does not exist in TaskList.
     */
    public Task deleteTask(Task chosen) throws DukeException {
        this.tasks.remove(chosen);
        return chosen;
    }

    public ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> selected = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                selected.add(task);
            }
        }
        return selected;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
