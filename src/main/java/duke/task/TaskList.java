package duke.task;

import duke.Duke;
import duke.DukeException;

import java.util.List;

public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor for the task list.
     * @param list A list of tasks.
     */
    public TaskList(List<Task> list) {
        taskList = list;
    }

    /**
     * Returns the arraylist of tasks.
     * @return Arraylist of tasks.
     */
    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Add a given task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Lists out all the tasks in the list in chronological order.
     *
     * @return The string representation of the list.
     * @throws DukeException If there is no task available.
     */
    public String listTasks() throws DukeException {

        if (taskList.size() == 0) {
            throw new DukeException("No tasks available.");
        }

        return this.toString();
    }

    /**
     * Returns the string representation of the task list.
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            stringList.append((i + 1) + ". " + task);

            if (i < taskList.size() - 1) {
                stringList.append(System.lineSeparator());
            }
        }

        return stringList.toString();
    }


    private void checkIndex(int idx) throws DukeException {
        if (idx < 0 || idx >= taskList.size()) {
            throw new DukeException("Task index out of bounds.");
        }
    }

    /**
     * Mark the task with the given index as done.
     * Task are 0-indexed.
     *
     * @param idx Index of the task to be marked.
     * @throws DukeException If index is out of bounds.
     */
    public void markTask(int idx) throws DukeException {
        checkIndex(idx);
        Task t = taskList.get(idx);
        t.markAsDone();
    }

    /**
     * Mark the task with the given index as undone.
     * Task are 0-indexed.
     *
     * @param idx Index of the task to be marked.
     * @throws DukeException If index is out of bounds.
     */
    public void unmarkTask(int idx) throws DukeException {
        checkIndex(idx);
        Task t = taskList.get(idx);
        t.markAsUndone();
    }

    /**
     * Deletes the task with the same index.
     *
     * @param idx Index of the task in the list.
     * @return Task deleted.
     * @throws DukeException If index is out of bounds.
     */
    public Task deleteTask(int idx) throws DukeException {
        checkIndex(idx);
        Task t = taskList.get(idx);
        taskList.remove(t);
        return t;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Compares an object to this task list.
     *
     * @param o Object to be compared with.
     * @return True if the given object is equal to this task list in value.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TaskList)) {
            return false;
        }

        // Typecast o to TaskList since we know it is of type TaskList
        TaskList task = (TaskList) o;

        return task.taskList.equals(this.taskList);
    }

    /**
    * Finds all the tasks matching the keyword.
    *
    * @param keyword Keyword to find the tasks.
    * @return String representation of all the matching tasks.
    */
    public String findTasks(String keyword) throws DukeException {
        StringBuilder foundTasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String description = t.getDescription();

            if (description.contains(keyword)) {
                foundTasks.append((i + 1) + ". " + t);

                if (i < taskList.size() - 1) {
                    foundTasks.append(System.lineSeparator());
                }
            }


        }

        if (foundTasks.length() == 0) {
            throw new DukeException("No matching task found.");
        }

        return foundTasks.toString();
    }

}
