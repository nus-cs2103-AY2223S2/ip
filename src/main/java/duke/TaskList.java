package duke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import duke.tasks.Task;

/**
 * Represents a list of Task to store users' tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object with ArrayList as data structure.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task into task list. Notifications will be printed if this
     * is not an action done by auto save and load.
     *
     * @param task   Task to be added to the list
     * @param isLoad Indicates if the action is done by auto save and load
     * @return Message of adding a new task.
     */
    public String addTask(Task task, boolean isLoad) {
        assert task == null : "Task can't be null!";
        tasks.add(task);
        if (!isLoad) {
            return "Got it. I've added this task:\n"
                            + "  " + task + "\n"
                            + "Now you have " + tasks.size() + " task(s) in the list.";
        } else {
            return "";
        }
    }

    /**
     * Deletes a task from the task list
     *
     * @param taskId Id of the task to be deleted
     * @return Message of deleting specified task.
     */
    public String deleteTask(int taskId) {
        String msg = "Noted. I've removed this task:\n"
                        + "  " + tasks.get(taskId) + "\n"
                        + "Now you have " + (tasks.size() - 1) + " task(s) in the list.";
        tasks.remove(taskId);
        return msg;
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
     * Returns current task list in String.
     *
     * @return String of task list.
     */
    public String printList() {
        StringBuilder tasksList = new StringBuilder("Here are the tasks in your list:\n");
        AtomicInteger count = new AtomicInteger(1);
        tasks.forEach(
                task -> tasksList.append(count.getAndIncrement())
                        .append(". ")
                        .append(task)
                        .append("\n"));
        return tasksList.toString();
    }

    /**
     * Filters tasks with the given keyword.
     *
     * @param keywords Keywords for searching matching tasks.
     * @return String of matching task(s).
     */
    public String findMatchingTasks(String... keywords) {
        assert keywords.length <= 0 : "no input keywords to find!";
        StringBuilder tasksList = new StringBuilder("Here are the matching task(s): \n");
        int count = 1;
        for (Task task : tasks) {
            for (String kw : keywords) {
                if (task.toString().contains(kw)) {
                    tasksList.append(count++).append(". ").append(task).append("\n");
                    break;
                }
            }
        }
        return tasksList.toString();
    }

    /**
     * Sort tasks by completion status, then by the lexicographic order of their description.
     * This will also print the sorted tasks list.
     * @return String of sorted tasks list.
     */
    public String sortTasks() {
        Collections.sort(tasks, Comparator.comparing(Task::isTaskDone).thenComparing(Task::getDescription));
        return printList();
    }
}
