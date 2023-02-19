package james.task;

import java.util.ArrayList;

/** This class encapsulates a TaskList object. */
public class TaskList {
    /** The list of user's tasks. */
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    /**
     * Sets the userToDoList to tasks.
     *
     * @param tasks The updated task list.
     */
    public void setTaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }



    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at index i of the task list.
     *
     * @param i The index of the task to be returned.
     * @return The task at index i of the task list
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The new task added.
     */
    public void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Removes the task at the specified position in task list.
     *
     * @param i The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task remove(int i) {
        return taskList.remove(i);
    }

    /**
     * Returns the string representation of the task list displayed
     * to the user.
     *
     * @return The string representation of a TaskList.
     */
    public String taskListToString() {
        int count = taskList.size();

        if (count == 0) {
            return "There are currently no tasks, please input tasks";
        }

        String taskString = "";

        for (int i = 0; i < count; i++) {
            String listItem = " " + (i + 1) + ". " + taskList.get(i).toString();

            taskString = taskString + "\n" + listItem;
        }

        return taskString;
    }

    //@@author elizabethhky@github.com-reused
    //Reused from https://github.com/nus-cs2103-AY2223S1/ip/commit/81e454e31671a1bfe795abd6ce624d5b9b51060c
    // with minor modifications
    /**
     * Returns the TaskList represented by a string to be stored in the taskList
     * file in the storage file.
     *
     * @return The string representation of stored TaskList.
     */
    public String taskListToStoreString() {
        int count = taskList.size();
        String storageOfTaskList = "";

        for (int i = 0; i < count; i++) {
            String taskInput = taskList.get(i).toStoreString();

            if (i == 0) {
                storageOfTaskList = taskInput;
            } else {
                storageOfTaskList = storageOfTaskList + "\n" + taskInput;
            }
        }

        return storageOfTaskList;
    }
    //@@author
}
