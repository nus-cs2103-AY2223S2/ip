package duke;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class representing a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Default constructor to create a TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Parameterized constructor to create a TaskList
     * @param init the initial list containing previously saved tasks
     */
    public TaskList(ArrayList<? extends Task> init) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(init);
    }

    /**
     * Deletes the Task at a specified index and returns the deleted task
     * @param index the index of the Task to be deleted
     * @return the deleted Task
     */
    public Task delete(int index) {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        return deletedTask;
    }

    /**
     * Marks the Task at a specified index as done
     * @param index the index of the Task to be marked as done
     */
    public void mark(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Marks the Task at a specified index as undone
     * @param index the index of the Task to be marked as undone
     */
    public void unmark(int index) {
        taskList.get(index).markAsUndone();
    }

    /**
     * Adds a Task to the list
     * @param task the Task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the Task at a specified index
     * @param index the index of the Task to be returned
     * @return the Task at the specified index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list
     * @return the size of the list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Finds all tasks matching a specified keyword
     * @param keyword the keyword to be matched
     * @return a list of tasks containing all matching tasks
     */
    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (Task task : taskList) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Returns whether the TaskList is empty
     * @return true if the TaskList is empty, false if it is not
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public TaskList sortDeadlines() {
        ArrayList<Deadline> deadlineList = new ArrayList<>();
        Comparator<Deadline> comparator = Comparator.comparing(d -> d.by);
        for (Task t : taskList) {
            if (t instanceof Deadline) {
                deadlineList.add((Deadline) t);
            }
        }
        deadlineList.sort(comparator);

        return new TaskList(deadlineList);
    }
}
