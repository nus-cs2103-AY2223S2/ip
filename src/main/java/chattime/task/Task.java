package chattime.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents task objects created by user's command.
 * Class is extended by Todo, Deadline and Event classes.
 */
public class Task {

    private static int tasksCount = 0;

    private String description;
    private boolean isDone;

    /**
     * Available types of tasks.
     */
    enum TaskType {
        TODO("T"), DEADLINE("D"), EVENT("E");

        private String taskCode;

        TaskType(String code) {
            taskCode = code;
        }

        @Override
        public String toString() {
            return taskCode;
        }
    }

    /**
     * Creates Task object with corresponding description.
     *
     * @param content The task description.
     */
    public Task(String content) {
        description = content;
        isDone = false;
        tasksCount++;
    }

    /**
     * Getter for task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the symbol relevant to current task's done status.
     *
     * @return The symbol depends on done status of task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns current task's done status.
     *
     * @return The done status of task.
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Compares current task's description with the given keyword.
     *
     * @return The result of comparison, true if matched, false otherwise.
     */
    public boolean isMatchDescription(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Sets done status of current task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets done status of current task as not done.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Gets total available tasks number.
     *
     * @return The total number of undeleted task.
     */
    public static int getCount() {
        return tasksCount;
    }

    /**
     * Reduces the total task number once a task is deleted.
     */
    public void removeTask() {
        tasksCount--;
    }

    /**
     * Returns conclusion message of current total number of tasks.
     *
     * @return The conclusion message indicates total number of tasks.
     */
    public static String printTotalTask() {
        return "Now you have " + tasksCount + " tasks in the list.";
    }

    /**
     * Generates a data string of task to be stored in storage file.
     *
     * @return The data string of task.
     */
    public String toDataString() {
        return String.format(" @ %d @ %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param date The user's input date.
     * @return false as default.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time The user's input time.
     * @return The check result of whether the input time and task deadline are the same.
     */
    public boolean isOnTime(LocalDate date, LocalTime time) {
        return false;
    }


    /**
     * Returns code and task name in a string.
     *
     * @return The string of code and task name for schedule use.
     */
    public String taskWithCode() {
        return toString();
    }

    /**
     * Checks whether the new task is already existed.
     *
     * @return The check result of whether duplicates exist.
     */
    public boolean isDuplicate(Task task) {
        return task.getDescription().equals(getDescription());
    }

    /**
     * Removes duplicated items.
     */
    public static void removeDuplicate() {
        tasksCount--;
    }

    /**
     * Returns current data of task.
     *
     * @return The current situation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
