package seedu.duke;

import java.util.ArrayList;

/**
 * Represents the TaskList object.
 */
public class TaskList {
    /** The TaskList object */
    protected ArrayList<Task> taskList;
    /** Size of the task list */
    protected int size;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Method to retrieve the Task at the given index.
     *
     * @param index index of the task to retrieve.
     * @return      retrieved task.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Method to remove the Task at the given index.
     *
     * @param index index of the task to remove.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Method to retrieve the current size of the TaskList.
     *
     * @return size of the TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Method to add a Task to the TaskList.
     *
     * @param t details of the Task to add to the TaskList.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
        this.size = this.size + 1;
    }

    /**
     * Method to mark at the given index (description) the task as done.
     *
     * @param taskList    the TaskList to mark the object.
     * @param description takes the String to parse into an integer to use as the index.
     */
    public static void markTask(TaskList taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.displayMarked(taskList, index);
            } else {
                Ui.line();
                System.out.println("Please enter a valid number!");
                Ui.checkList(taskList);
                Ui.line();
            }
        } catch (NumberFormatException e) {
            System.out.println("enter valid number");
        }
    }

    /**
     * Method to unmark at the given index (description) the task as done.
     *
     * @param taskList    the TaskList to unmark the object.
     * @param description takes the String to parse into an integer to use as the index.
     */
    public static void unmarkTask(TaskList taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.displayUnmarked(taskList, index);
            } else {
                Ui.line();
                System.out.println("Please enter a valid number!");
                Ui.checkList(taskList);
                Ui.line();
            }
        } catch (NumberFormatException e) {
            System.out.println("enter valid number");
        }
    }

    /**
     * Method to remove the task at the given index (description.
     *
     * @param taskList    the TaskList to remove the object from.
     * @param description takes the String to parse into an integer to use as the index.
     */
    public static void removeTask(TaskList taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.displayDelete(taskList, index);
            } else {
                Ui.line();
                System.out.println("Please enter a valid number!");
                Ui.checkList(taskList);
                Ui.line();
            }
        } catch (NumberFormatException e) {
            System.out.println("enter valid number");
        }
    }
}
