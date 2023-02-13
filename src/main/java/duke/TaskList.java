package duke;

import duke.Task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class that stores the tasks that users input.
 */
public class TaskList {
    private List<Task> tasks;
    protected int items;

    /**
     * Constructor to create a new task list object from scratch.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to create a new task list object from an existing list.
     * @param tasks The existing list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.items = tasks.size();
    }

    /**
     * Outputs the existing list of tasks.
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Outputs the existing number of tasks in the list.
     * @return The number of tasks.
     */
    public int getItems() {
        return this.items;
    }

    /**
     * Takes in a task which to add on to the task list, and returns a confirmation message to show the task is
     * successfully added.
     * @param task The task to add
     * @return The confirmation message.
     */
    public String addTask(Task task) {
        tasks.add(task);
        items++;
        return "Got it. I've added this task:\n"
                + String.format(" [%s][ ] %s\n Now you have %s tasks in the list.", task.getTaskType(), task, items);
    }

    /**
     * Takes in a number of the task which to delete from the task list,
     * and returns a confirmation message to show the task is successfully deleted.
     * @param taskNumber The task to delete
     * @return The confirmation message.
     */
    public String deleteTask(int taskNumber) {
        Task removedTask = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        items--;
        return "Noted. I've removed this task:\n"
                + String.format(" [%s][%s] %s\n Now you have %s tasks in the list.",
                removedTask.getTaskType(), removedTask.getStatusIcon() , removedTask, items);
    }

    /**
     * Finds all task in the current task list containing a certain keyword, and returns them in a String.
     * @param keyword Keyword to query.
     * @return List of tasks in list containing the keyword.
     */
    public String findKeyword(String keyword) {
        StringBuilder tasksWithKeyword = new StringBuilder();
        tasksWithKeyword.append("Here are the tasks in your list containing the keyword " + keyword + ":\n");
        int keywordCounter = 1;

        for (Task taskToCheck : tasks) {
            if (taskToCheck.toString().contains(keyword)) {
                String taskWithKeyword = String.format("%s.[%s][%s] %s\n", keywordCounter, taskToCheck.getTaskType(),
                        taskToCheck.getStatusIcon(), taskToCheck.toString());
                tasksWithKeyword.append(taskWithKeyword);
                keywordCounter++;
            }
        }

        return tasksWithKeyword.toString();
    }

    /**
     * Prints out all the items in the current task list in String form.
     * @return The current items in the task list.
     */
    public String printTaskList() {
        StringBuilder tasklist = new StringBuilder();
        tasklist.append("Here are the tasks in your list:" + "\n");

        for (int i = 0; i < items; i++) {
            String taskToAdd = String.format("%s.[%s][%s] %s\n", i + 1, tasks.get(i).getTaskType(),
                    tasks.get(i).getStatusIcon(), tasks.get(i).toString());
            tasklist.append(taskToAdd);
        }

        return tasklist.toString();
    }
}
