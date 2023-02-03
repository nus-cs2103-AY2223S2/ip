package seedu.duke;

import java.util.ArrayList;

/**
 * Represents the TaskList object.
 */
public class TaskList {
    protected ArrayList<Task> taskList;
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
        assert this.size >= 0: "TaskList length cannot be negative.";
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
     *
     * @return String to mark task as done.
     */
    public static String markTask(TaskList taskList, String description) {
        String response;
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                taskList.get(index).markAsDone();
                response = Ui.displayMarked(taskList, index);
            } else {
                response = "Please enter a valid number!\n" + Ui.checkList(taskList);
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid number!";
        }
        return response;
    }

    /**
     * Method to unmark at the given index (description) the task as done.
     *
     * @param taskList    the TaskList to unmark the object.
     * @param description takes the String to parse into an integer to use as the index.
     *
     * @return String to mark task as undone.
     */
    public static String unmarkTask(TaskList taskList, String description) {
        String response;
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                taskList.get(index).markAsUndone();
                response = Ui.displayUnmarked(taskList, index);
            } else {
                response = "Please enter a valid number!\n" + Ui.checkList(taskList);
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid number!";
        }
        return response;
    }

    /**
     * Method to remove the task at the given index (description).
     *
     * @param taskList    the TaskList to remove the object from.
     * @param description takes the String to parse into an integer to use as the index.
     *
     * @return String to mark task as removed.
     */
    public static String removeTask(TaskList taskList, String description) {
        String response;
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                response = Ui.displayDelete(taskList, index);
                taskList.remove(index);
            } else {
                response = "Please enter a valid number!\n" + Ui.checkList(taskList);
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid number!";
        }
        return response;
    }

    /**
     * Method to find the task from a given keyword.
     *
     * @param keyword the keyword of the tasks to be found.
     *
     * @return TaskList of found tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList foundList = new TaskList();
        for (Task t: taskList) {
            if (t.description.contains(keyword)) {
                foundList.addTask(t);
            }
        }
        return foundList;
    }
}
