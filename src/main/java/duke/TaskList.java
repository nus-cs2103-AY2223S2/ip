package duke;

import java.util.ArrayList;
import java.util.Collection;

import duke.task.Task;

/**
 * Class representing the list of Tasks.
 */
public class TaskList {
    protected static final BadCommandException INDEX_OOB_ERROR =
            new BadCommandException("Index given is out of bounds!");
    protected ArrayList<Task> tasks;

    /**
     * Initialises an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialises an TaskList containing some Tasks.
     */
    public TaskList(Collection<Task> initTasks) {
        tasks = new ArrayList<>(initTasks);
    }

    private boolean isIndexInRange(int idx) {
        return idx < tasks.size() && idx >= 0;
    }

    /**
     * Adds a task.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a task.
     *
     * @param idx The index of the task to be removed.
     * @return The removed task.
     * @throws BadCommandException If the idx given is OOB.
     */
    public Task removeTask(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        Task deletedTask = tasks.get(idx);
        tasks.remove(idx);
        return deletedTask;
    }

    /**
     * Marks a task as done.
     *
     * @param idx Index of the task.
     * @throws BadCommandException If the idx given is OOB.
     */
    public void markTaskAsDone(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        tasks.get(idx).markAsDone();
    }

    /**
     * Unmarks a task as done.
     *
     * @param idx Index of the task.
     * @throws BadCommandException If the idx given is OOB.
     */
    public void unmarkTaskAsDone(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        tasks.get(idx).unmarkAsDone();
    }

    /**
     * Retrieves a task.
     *
     * @param idx Index of the task.
     * @throws BadCommandException If the idx given is OOB.
     */
    public Task getTask(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        return tasks.get(idx);
    }

    /**
     * Retreives tasks using a keyword.
     *
     * @param keyword Keyword to be given.
     * @return TaskList containing the matching tasks.
     */
    public TaskList getTasksByKeyword(String keyword) {
        String trimmedKeyword = keyword.trim().toLowerCase();
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            String taskString = task.toString().toLowerCase();
            if (task.toString().toLowerCase().contains(trimmedKeyword)
                    || trimmedKeyword.contains(taskString)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder listOutput = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append(String.format(
                    "%d. %s",
                    i + 1,
                    tasks.get(i)
            ));
            if (i < tasks.size() - 1) {
                listOutput.append("\n");
            }
        }
        return listOutput.toString();
    }
}
