package james.task;

import java.util.ArrayList;

/** This class encapsulates a TaskList object. */
public class TaskList {
    /** The list of user's tasks. */
    public ArrayList<Task> taskList;

    /**
     * Constructor for TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void markTask(int index) {
        taskList.get(index).markDone();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
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
            return "\nThere are currently no tasks, please input tasks";
        }

        String taskString = "";

        for (int i = 0; i < count; i++) {
            String listItem = " " + String.valueOf(i + 1) + ". " + taskList.get(i).toString();

            taskString = taskString + "\n" + listItem;
        }

        return taskString;
    }
    public int getSize() {
        return taskList.size();
    }




    /**
     * Returns the TaskList represented by a string to be stored in the taskList
     * file in user's hard disk.
     *
     * @return The string representation of stored TaskList.
     */
    public String taskListToStoreString() {
        int count = taskList.size();
        String taskListStorage = "";

        for (int i = 0; i < count; i++) {
            String taskItem = taskList.get(i).toStoreString();

            if (i == 0) {
                taskListStorage = taskItem;
            } else {
                taskListStorage = taskListStorage + "\n" + taskItem;
            }
        }

        return taskListStorage;
    }
}
