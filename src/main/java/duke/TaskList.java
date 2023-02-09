package duke;

import java.util.ArrayList;

/**
 * Represents a list of all tasks.
 */
public class TaskList {
    ArrayList<Task> taskArray;
    private Ui ui;

    public TaskList() {
        taskArray = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Retrieves the total number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int getNumberOfTasks() {
        return taskArray.size();
    }

    /**
     * Retrieves the task at a specific index of the list.
     *
     * @param index Index of the list at which the task to be retrieved is at.
     * @return Task at the index of the list.
     */
    public Task getTask(int index) {
        return taskArray.get(index);
    }

    /**
     * Adds new task to the list.
     *
     * @param task Task to be added into the list.
     */
    public void addTask(Task task) {
        taskArray.add(task);
    }


    public String getTaskList() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskArray.size(); i++) {
            result += i + 1 + "." + taskArray.get(i) + "\n";
        }
        return result;
    }

    public String mark(String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task toMarkDone = taskArray.get(number);

        toMarkDone.markAsDone();
        return ui.showMark(toMarkDone);
    }

    public String unMark(String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task toUnmarkDone = taskArray.get(number);

        toUnmarkDone.unMarkAsDone();
        return ui.showUnmark(toUnmarkDone);
    }

    public String deleteTask(String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        String result = "";
        try {
            Task taskToDelete = taskArray.get(number);
            taskArray.remove(number);
            result = ui.showDelete(taskToDelete, taskArray.size());
            return result;
        } catch (IndexOutOfBoundsException e1) {
            if (number < 1) {
                return "There is no such task.";
            } else {
                return "There are only " + taskArray.size() + " tasks in the list.";
            }
        }
    }
}
