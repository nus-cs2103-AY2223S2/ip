package duke.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A TaskList class that encapsulates the information and actions of a task list.
 */
public class TaskList {
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
     * Constructs a TaskList object by copying the values from an existing TaskList object.
     *
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
     * Clears the task list by creating a new empty ArrayList.
     */
    public void clearTasks() {
        this.tasks = new ArrayList<>();
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
     * Check if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
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

        // return a new taskList containing the filtered and sorted tasks
        return new TaskList(new ArrayList<>(result));
    }

    /**
     * Categorizes the tasks in the task list into four different task lists: deadline, event,
     * fixed duration, and to-do.
     *
     * @return an array of task lists containing the categorized tasks. The order of the task lists in the array
     *      is [deadline, event, fixed duration, to-do].
     */
    public TaskList[] categorizeTask() {
        TaskList[] listOfLists = new TaskList[4];

        // Initialize task lists for each task type
        TaskList deadlineList = new TaskList();
        TaskList eventList = new TaskList();
        TaskList fixedDurationList = new TaskList();
        TaskList todoList = new TaskList();

        // Iterate through all tasks in the master task list
        for (DukeTask task : this.tasks) {
            if (task.getType() == TaskType.DEADLINE) {
                // Add task to deadline list if it is a deadline task
                deadlineList.addTask(task);
            } else if (task.getType() == TaskType.EVENT) {
                // Add task to eventList if it is an event task
                eventList.addTask(task);
            } else if (task.getType() == TaskType.FIXED_DURATION) {
                // Add task to fixed duration list if it is a fixed duration task
                fixedDurationList.addTask(task);
            } else if (task.getType() == TaskType.TODO) {
                // Add task to to-do list if it is a to-do task
                todoList.addTask(task);
            }
        }

        // Add all task lists to the list of lists
        listOfLists[0] = deadlineList;
        listOfLists[1] = eventList;
        listOfLists[2] = fixedDurationList;
        listOfLists[3] = todoList;

        return listOfLists;
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
}
