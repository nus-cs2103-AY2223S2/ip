package duke;

import java.util.ArrayList;

/**
 * A class that allows operations on a list of tasks.
 */
public class TaskList {

    /** ArrayList used to store all the task. */
    protected ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     * Initialises the new empty TaskList.
     */
    public TaskList() {
        //Default constructor for empty Duke.TaskList
        tasks = new ArrayList<Task>();
    }

    /**
     * Method to add task directly.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Method to add new ToDo task.
     * @param taskDesc The name of the ToDo.
     * @param taskStatus The boolean status of the ToDo.
     */
    public void addTask(String taskDesc, boolean taskStatus) {
        tasks.add(new ToDo(taskDesc, taskStatus));
    }

    /**
     * Method to add new Deadline task.
     * @param taskDesc The name of the Deadline.
     * @param taskBy The deadline of the Deadline.
     * @param taskStatus The boolean status of the Deadline.
     */
    public void addTask(String taskDesc, String taskBy, boolean taskStatus) {
        tasks.add(new Deadline(taskDesc, taskBy, taskStatus));
    }

    /**
     * Method to add new Event task.
     * @param taskDesc The name of the Event..
     * @param taskFrom The starting time of the Event.
     * @param taskTo The ending time of the Event.
     * @param taskStatus The boolean status of the Event.
     */
    public void addTask(String taskDesc, String taskFrom, String taskTo, boolean taskStatus) {
        tasks.add(new Event(taskDesc, taskFrom, taskTo, taskStatus));
    }

    /**
     * Method to list out all the task currently in the list and return as a string..
     *
     * @return The list of results in a String.
     */
    public String returnTaskAsString() {
        if (tasks.size() == 0) {
            return "No task in task list at the moment.";
        }
        String result;
        result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Method to delete the selected task.
     *
     * @param index The index of the to be deleted task
     * @return String representation of the task that is removed.
     */
    public String deleteTaskWithResult(int index) throws DukeException {
        //Cache task for displaying.
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Task index out of my range!");
        }
        Task t = tasks.get(index - 1);
        tasks.remove(index - 1);
        return t + " is now gone!";
    }

    /**
     * Method to mark the selected task as done.
     *
     * @param index The index of the to be marked task
     * @return String representation of the task that is marked.
     */
    public String markTaskWithResult(int index) {
        tasks.get(index - 1).markDone();
        return "I marked this task as done:\n" + tasks.get(index - 1);
    }

    /**
     * Method to unmark the selected task as done.
     *
     * @param index The index of the to be unmarked task
     * @return String representation of the task that is unmarked.
     */
    public String unMarkTaskWithResult(int index) {
        tasks.get(index - 1).markUndone();
        return "I marked this task as undone:\n" + tasks.get(index - 1);
    }

    /**
     * Method to return the last task added to the list as String.
     *
     * @return String representation of the newest task added as String.
     */
    public String returnNewestTaskAsString() {
       return "Task added:\n"
                + tasks.get(tasks.size() - 1)
                + "\nTotal task now: "
                + tasks.size();
    }

    /**
     * Method to search through the task list to find a specific task
     * @param searchString The string to search with.
     * @return The String representation of what is found in the TaskList.
     */
    public String searchTaskWithResult(String searchString) {
        String res;
        res = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskDesc.contains(searchString)) {
                res = res + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }

        if (res.equals("")) {
            return "Found nothing matching your condition!";
        }

        return res;
    }
}
