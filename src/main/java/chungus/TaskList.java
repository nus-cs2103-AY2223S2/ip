package chungus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Wraps a list of tasks.
 */
class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for a task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Another constructor for a task list.
     * 
     * @param _tasks A list of tasks.
     */
    public TaskList(List<Task> _tasks) {
        tasks = new ArrayList<>(_tasks);
    }

    /**
     * Runs some observer function for each task in the list.
     * 
     * @param f Some biconsumer function. The second argument is the task's index in
     *          the internal list.
     */
    public void forEach(BiConsumer<Task, Integer> f) {
        for (int i = 0; i < tasks.size(); i++) {
            f.accept(tasks.get(i), i);
        }
    }

    /**
     * Creates a new task list by applying some predicate to the current list.
     * 
     * @param f The predicate to apply.
     * @return The new filtered list.
     */
    public TaskList filter(Predicate<Task> f) {
        return new TaskList(tasks.stream().filter(f).collect(Collectors.toList()));
    }

    /**
     * Converts the entire list into a serialized representation.
     * 
     * @return The serialized string.
     */
    public String marshal() {
        return tasks.stream()
                .map(task -> task.marshal())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list.
     */
    public int count() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The new task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task by index.
     * 
     * @param idx The task's index.
     * @return The actual task.
     * @throws TaskNotFoundException If the index is out of bounds.
     */
    public Task get(int idx) {
        try {
            return tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(idx);
        }
    }

    /**
     * Mark a task as done.
     * 
     * @param idx The task's index.
     */
    public void setDone(int idx) {
        get(idx).setDone();
    }

    /**
     * Mark a task as not done.
     * 
     * @param idx The task's index.
     */
    public void setNotDone(int idx) {
        get(idx).setNotDone();
    }

    /**
     * Removes a task and returns it.
     * 
     * @param idx The task's index.
     * @return The removed task.
     */
    public Task remove(int idx) {
        Task ret = get(idx);
        tasks.remove(idx);
        return ret;
    }

    @Override
    public boolean equals(Object _other) {
        if (!(_other instanceof TaskList)) {
            return false;
        }
        TaskList other = (TaskList) _other;
        if (this.tasks.size() != other.tasks.size()) {
            return false;
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            if (!(this.tasks.get(i).equals(other.tasks.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
