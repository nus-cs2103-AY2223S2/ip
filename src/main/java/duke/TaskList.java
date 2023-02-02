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
            return tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
    }

    /**
     * Removes task from TaskList.
     *
     * @param chosen task to be removed.
     * @throws DukeException if Task does not exist in TaskList.
     */
    public void deleteTask(Task chosen) throws DukeException {
        this.tasks.remove(chosen);
    }

    /**
     * Finds all matching tasks containing the keyword
     *
     * @param keyword keyword passed in by user
     * @return list of tasks matching the keyword
     */
    public String getMatchingTasksString(String keyword) {
        StringBuilder res = new StringBuilder();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                int idx = tasks.indexOf(task) + 1;
                res.append(String.format("%d.%s", idx, task));
            }
        }
        if (res.toString().equals("")) {
            return "There are no matching tasks currently!";
        } else {
            return "Here are the matching tasks in your list:\n" + res;
        }
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int idx = 1;
        for (Task task : tasks) {
            result.append(String.format("%d.%s", idx, task));
            idx++;
        }
        return result.toString();
    }

    /**
     * Returns representation of task list to be stored into txt file
     *
     * @return string representation of task list.
     */
    public String saveString() {
        StringBuilder result = new StringBuilder();
        for (Task task: tasks) {
            result.append(task.getText());
            result.append("\n");
        }
        return result.toString();
    }
}
