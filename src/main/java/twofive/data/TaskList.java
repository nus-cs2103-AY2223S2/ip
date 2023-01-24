package twofive.data;

import twofive.exception.InvalidTaskException;
import twofive.exception.TaskDoneException;
import twofive.exception.TaskUndoneException;
import twofive.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of tasks currently added.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a String containing all tasks currently added, including
     * their type, whether it is done and the description.
     * Contains the deadline for Deadline tasks.
     * Contains the start time and end time for Event tasks.
     *
     * @return String containing all added tasks.
     */
    public String getTasksList() {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        int taskIndex = 1;
        for (Task task : tasks) {
            stringBuilder.append(taskIndex + ". " + task);
            if (taskIndex - 1 < tasks.size() - 1) {
                stringBuilder.append("\n");
            }
            taskIndex++;
        }
        return stringBuilder.toString();
    }

    /**
     * Returns number of tasks currently added.
     *
     * @return Number of tasks in the list.
     */
    public int getTasksNum() {
        return tasks.size();
    }

    /**
     * Deletes a task from the list given its number in the list.
     *
     * @param taskNum Number of task in the list.
     */
    public Task deleteTask(int taskNum) {
        Task currentTask = tasks.get(taskNum);
        tasks.remove(currentTask);
        return currentTask;
    }

    /**
     * Sets a task as done given its number in the list.
     *
     * @param taskNum Number of task in the list.
     * @throws TaskDoneException If task is already done.
     */
    public Task setTaskAsDone(int taskNum) throws TaskDoneException {
        Task currentTask = tasks.get(taskNum);
        currentTask.setDone();
        return currentTask;
    }

    /**
     * Sets a task as not done given its number in the list.
     *
     * @param taskNum Number of task in the list.
     * @throws TaskUndoneException If task has not been done.
     */
    public Task setTaskAsUndone(int taskNum) throws TaskUndoneException {
        Task currentTask = tasks.get(taskNum);
        currentTask.setUndone();
        return currentTask;
    }

    /**
     * Adds a given Task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns a String containing all tasks which have a deadline, start time
     * or end time on the given date, including their type, whether it is
     * done and the description.
     * Contains the deadline for Deadline tasks.
     * Contains the start time and end time for Event tasks.
     *
     * @param date Date in which tasks are due
     * @return String containing all added tasks with a deadline on the given date.
     */
    public String getTasksOnDateList(LocalDate date) {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list due on "
                + date.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy")) + ":\n");
        int taskIndex = 1;
        int numTasksDue = 0;
        for (Task task : tasks) {
            if (task.isToday(date)) {
                numTasksDue++;
            }
        }
        for (Task task : tasks) {
            if (task.isToday(date)) {
                stringBuilder.append(taskIndex + ". " + task);
                if (taskIndex - 1 < numTasksDue - 1) {
                    stringBuilder.append("\n");
                }
                taskIndex++;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Returns a String containing all tasks to be saved into a file.
     *
     * @return String containing all added tasks for saving into a file.
     */
    public String getSaveTasksString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.getFileWriteString() + "\n");
        }
        return stringBuilder.toString();
    }
}
