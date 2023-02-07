package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents the list of task in the current session.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialise the ArrayList to store the task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Generate the tasks in the Arraylist.
     *
     * @return tasks List of all task.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task object directly to the task list.
     *
     * @param t Task to add to the list.
     */
    public void addTask(Task t) {
        assert t != null;
        tasks.add(t);
    }

    /**
     * Returns a string representation of the list of tasks.
     *
     * @return String representation of the task list
     */
    public String listTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += "     " + (i + 1) + ". " + tasks.get(i).toString();
            if (i < tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Marks a task as done given the task number.
     *
     * @param taskNum Task number of task to mark.
     * @throws DukeException If the task number is invalid.
     */
    public void markTask(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number invalid");
        }
        tasks.get(taskNum).mark();
    }

    /**
     * Marks a task as undone given the task number.
     *
     * @param taskNum Task number of task to mark.
     * @throws DukeException If the task number is invalid.
     */
    public void unmarkTask(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number invalid");
        }
        tasks.get(taskNum).unmark();
    }

    /**
     * Removes a task given the task number.
     *
     * @param taskNum Task number of task to remove.
     * @return Success message.
     * @throws DukeException If the task number is invalid.
     */
    public Task deleteTask(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number invalid");
        }
        return tasks.remove(taskNum);
    }

    /**
     * Retrieves the size of the task .
     *
     * @return size number of task.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves the latest task.
     *
     * @return task task that is the latest.
     */
    public Task getLatestTask() {
        assert !tasks.isEmpty();
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Retrieves the task according to the index in the array.
     *
     * @param taskNum Task number of task to be retrieved.
     * @return tasks task according to the index.
     */
    public Task getTask(int taskNum) {
        assert !tasks.isEmpty();
        return tasks.get(taskNum);
    }

    public ArrayList<Task> getTasksWithKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.nameContainsKeyword(keyword)) {
                result.add(t);
            }
        }

        return result;
    }
}
