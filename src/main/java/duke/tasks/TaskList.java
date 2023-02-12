package duke.tasks;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList represents the list of tasks.
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Returns a list of tasks saved.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Edit a list of tasks.
     *
     * @param taskList New list of the tasks.
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Return a task from a list of tasks.
     *
     * @param index Index of task to retrieve.
     * @return A specified task.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Return size of list of tasks.
     *
     * @return number of tasks in list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Add task to list of tasks.
     *
     * @param newTask Add new task.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Removes the Task at the given index from the TaskList.
     *
     * @param index The given index.
     * @return The task removed.
     */
    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Marks the Task at the given index as uncompleted.
     *
     * @param index The given index.
     * @return The Task unmarked.
     */
    public Task markTask(int index) {
        Task task = taskList.get(index);
        assert task != null;
        task.mark();
        return task;
    }

    /**
     * Marks the Task at the given index as uncompleted.
     *
     * @param index The given index.
     * @return The Task unmarked.
     */
    public Task unmarkTask(int index) {
        Task task = taskList.get(index);
        assert task != null;
        task.unmark();
        return task;
    }

    /**
     * Find the Task with same wording as given string.
     *
     * @param s The given string.
     * @return A list of filtered task.
     */
    public List<Task> findTask(String s) {
        return taskList.stream()
                .filter(task -> task.getTask().contains(s))
                .collect(Collectors.toList());
    }

    /**
     * Returns a boolean based on if the task list is empty.
     *
     * @return True if task is empty else return false.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : taskList) {
            sb.append(i);
            sb.append(". ");
            sb.append(task);
            sb.append("\n");
            ++i;
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

}
