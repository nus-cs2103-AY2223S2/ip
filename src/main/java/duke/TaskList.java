package duke;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task into task list. Notifications will be printed if this
     * is not an action done by auto save and load.
     *
     * @param task Task to be added to the list
     * @param isLoad Indicates if the action is done by auto save and load
     */
    public void addTask(Task task, boolean isLoad) {
        tasks.add(task);
        if (!isLoad) {
            Ui.print(
                    "Got it. I've added this task:\n" +
                            "\t  " + task + "\n" +
                            "\tNow you have " + tasks.size() + " task(s) in the list."
            );
        }
    }

    /**
     * Deletes a task from the task list
     *
     * @param taskId Id of the task to be deleted
     */
    public void deleteTask(int taskId) {
        Ui.print(
                "Noted. I've removed this task:\n" +
                "\t  " + tasks.get(taskId) + "\n" +
                "\tNow you have " + (tasks.size() - 1) + " task(s) in the list."
        );
        tasks.remove(taskId);
    }

    /**
     * Returns a task with the input task Id
     *
     * @param taskId Id of the task
     * @return Task with the specified Id
     */
    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns size of current list
     *
     * @return size of the current list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints out all the tasks in the list
     */
    public void printList() {
        String tasksList = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            tasksList += "\t" + (i + 1) + ". " + tasks.get(i).toString();
            if (i < tasks.size() - 1) {
                tasksList += "\n";
            }
        }
        Ui.print(tasksList);
    }
}
