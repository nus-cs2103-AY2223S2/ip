package jarvis.task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import jarvis.exception.command.CommandParseException;
import jarvis.exception.command.InvalidIndexException;
import jarvis.exception.command.InvalidParameterException;

/**
 * Container class for tasks and their corresponding operations.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor for an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a task list populated with the given tasks.
     *
     * @param tasks Tasks to populate the list with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     * @return List of response lines.
     */
    public List<String> addTask(Task task) {
        assert task != null;
        this.tasks.add(task);
        return List.of(
                String.format("Got it! I've added task %d to the list.", this.tasks.size()),
                "\t" + task
        );
    }

    /**
     * Deletes a task from the list at the given index.
     *
     * @param index Index of the task to delete.
     * @return List of response lines.
     * @throws InvalidIndexException If the index to delete is invalid.
     */
    public List<String> deleteTask(int index) throws CommandParseException {
        if (this.tasks.isEmpty()) {
            throw new InvalidParameterException(
                    "TaskList is empty",
                    "There are no tasks to delete, please add a task first."
            );
        }
        if (index <= 0 || index > this.tasks.size()) {
            throw new InvalidIndexException(1, this.tasks.size());
        }
        Task task = this.tasks.remove(index - 1);
        return List.of(String.format("Got it, I've removed task %d.", index), "\t" + task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as done or undone as given.
     *
     * @param index 1-based index of the task.
     * @param isDone Whether the task is marked as done.
     * @return List of response lines.
     */
    public List<String> setTaskDone(int index, boolean isDone) throws CommandParseException {
        if (this.tasks.isEmpty()) {
            throw new InvalidParameterException(
                    "TaskList is empty",
                    "There are no tasks to mark, please add a task first."
            );
        }

        if (index <= 0 || index > this.tasks.size()) {
            throw new InvalidIndexException(1, this.tasks.size());
        }

        Task task = this.tasks.get(index - 1);
        task.setDone(isDone);
        return List.of(
                String.format("Got it! I've marked task %d as %s.", index, isDone ? "done" : "undone"),
                "\t" + task
        );
    }

    /**
     * Returns a list of response lines to display the task list.
     *
     * @return List of response lines.
     */
    public List<String> getTasksForPrint() {
        return this.getTasksForPrint(this.tasks);
    }

    /**
     * Returns a list of response lines to display the task list, after applying the given filter.
     *
     * @param filter List filter.
     * @return List of response lines.
     */
    public List<String> getTasksForPrint(TaskFilter filter) {
        // List of tasks after applying the filter
        List<Task> filteredTasks = this.tasks
                .stream()
                .filter(task -> task.satisfies(filter))
                .collect(Collectors.toList());
        return this.getTasksForPrint(filteredTasks);
    }

    /**
     * Returns a list of response lines to display the given tasks.
     *
     * @param tasks List of tasks.
     * @return List of response lines.
     */
    private List<String> getTasksForPrint(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return List.of("No tasks, you're good for the day!");
        }

        List<String> res = new LinkedList<>();
        res.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            res.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        return res;
    }
}
