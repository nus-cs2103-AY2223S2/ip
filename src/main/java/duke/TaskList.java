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
     * Method to print out all task in number point form.
     */
    public void printTask() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    public String returnTaskAsString() {
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
     */
    public void deleteTask(int index) {
        Task t = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println(t + " is now gone!");
    }

    public String deleteTaskWithResult(int index) {
        //Cache task for displaying.
        Task t = tasks.get(index - 1);
        tasks.remove(index - 1);
        return t + " is now gone!";
    }

    /**
     * Method to mark the selected task as done.
     *
     * @param index The index of the to be marked task
     */
    public void markTask(int index) {
        tasks.get(index - 1).markDone();
        System.out.println("I marked this task as done:\n" + tasks.get(index - 1));
    }

    public String markTaskWithResult(int index) {
        tasks.get(index - 1).markDone();
        return "I marked this task as done:\n" + tasks.get(index - 1);
    }

    /**
     * Method to unmark the selected task as done.
     *
     * @param index The index of the to be unmarked task
     */
    public void unMarkTask(int index) {
        tasks.get(index - 1).markUndone();
        System.out.println("I marked this task as undone:\n" + tasks.get(index - 1));
    }

    public String unMarkTaskWithResult(int index) {
        tasks.get(index - 1).markUndone();
        return "I marked this task as undone:\n" + tasks.get(index - 1);
    }

    /**
     * Method to print the last task added to the list.
     */
    public void printNewestTask() {
        System.out.println("Task added:\n"
                + tasks.get(tasks.size() - 1)
                + "\nTotal task now: "
                + tasks.size());
    }

    /**
     * Method to return the last task added to the list as String.
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
     */
    public void searchTask(String searchString) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).name.contains(searchString)) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public String searchTaskWithResult(String searchString) {
        String res;
        res = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).name.contains(searchString)) {
                res = res + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        return res;
    }
}
