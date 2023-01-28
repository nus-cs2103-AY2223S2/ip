package duke.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A TaskList class that encapsulates the information and actions of a task list.
 */
public class TaskList implements Cloneable {
    private ArrayList<DukeTask> tasks;

    /**
     * Constructor of the TaskList class that create new Arraylist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


     /**
     * Constructs a TaskList object with the given ArrayList of DukeTask.
     *
     * @param list ArrayList of DukeTask
     */
    public TaskList(ArrayList<DukeTask> list) {
        this.tasks = list;
    }

    /**
     * Constructs a TaskList object by copying the values from an existing TaskList object
     * @param other the existing TaskList object
     */
    public TaskList(TaskList other) {
        this.tasks = new ArrayList<>(other.tasks);
    }

    /**
     * Adds the given task to the TaskList.
     *
     * @param task The TaskList to be added
     */
    public void addTask(DukeTask task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified index in the task list and returns the task that was removed.
     *
     * @param taskIndex The index of the task to be removed
     * @return The task that was removed
     */

    public DukeTask deleteTask(int taskIndex) {
        DukeTask taskToDelete = tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return taskToDelete;
    }


    /**
     * Indicates the number of the task on the list.
     *
     * @return The number of the task on the list
     */
    public int getNoOfTasks() {
        return this.tasks.size();
    }

    /**
     * Gets the Task of the given index from the TaskList.
     *
     * @param index The index of the task to be obtained
     * @return The task of the given index
     */
    public DukeTask getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the ArrayList of DukeTask.
     *
     * @return The ArrayList of DukeTask
     */
    public ArrayList<DukeTask> getTasks() {
        return this.tasks;
    }

    /**
     * Sets the ArrayList of DukeTask.
     *
     * @param tasks The ArrayList of DukeTask
     */
    public void setTasks(ArrayList<DukeTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * Extracts all incomplete deadline tasks from the task list and returns them as a new TaskList.
     * The tasks are sorted by their deadlines in ascending order.
     *
     * @return A new TaskList containing all incomplete deadline tasks from the original list
     */
    public TaskList extractDeadlines() {
        // Create a stream of tasks
        List<DukeTask> result = this.tasks.stream()
                // filter the stream to only include DEADLINE tasks that are not done
                .filter(task -> task.getType() == TaskType.DEADLINE && !task.getStatus())
                // sort the filtered tasks based on their end date
                .sorted(Comparator.comparing(x -> {
                    DeadlineTask ddlTask = (DeadlineTask) x;
                    return ddlTask.getEndDate();
                }))
                // collect the sorted tasks into a list
                .collect(Collectors.toList());

        // return a new tasklist containing the filtered and sorted tasks
        return new TaskList(new ArrayList<>(result));
    }

    /**
     * Check if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
    
    /**
     * Returns a string representation of the task list in the format "index. task\n".
     *
     * @return A string representation of the task list
     */
    @Override
    public String toString() {
        // Creating a string builder to append task index and details
        StringBuilder listContent = new StringBuilder();
        // Iterating through the task list
        for (int i = 0; i < this.getNoOfTasks(); i++) {
            // Appending task index and task details
            listContent.append(i + 1).append(".").append(this.getTask(i)).append("\n");
        }
        // Return the final string representation of the task list
        return String.valueOf(listContent);
    }

    @Override
    public TaskList clone() {
        try {
            TaskList clone = (TaskList) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
