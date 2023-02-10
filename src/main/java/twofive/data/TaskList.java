package twofive.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import twofive.exception.TaskDoneException;
import twofive.exception.TaskUndoneException;
import twofive.task.Task;

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
     * Returns an ArrayList containing all tasks which have a deadline, start time
     * or end time on the given date, including their type, whether it is
     * done and the description.
     * Contains the deadline for Deadline tasks.
     * Contains the start time and end time for Event tasks.
     *
     * @param date Date in which tasks are due
     * @return ArrayList containing all added tasks with a deadline on the given date.
     */
    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.isToday(date))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a String containing all tasks to be saved into a file.
     *
     * @return String containing all added tasks for saving into a file.
     */
    public String getSaveTasksString() {
        StringBuilder stringBuilder = new StringBuilder();
        tasks.forEach(task -> stringBuilder.append(task.getFileWriteString() + "\n"));
        return stringBuilder.toString();
    }

    /**
     * Returns an ArrayList containing all tasks which have the specified keyword in their description,
     * including their type, whether it is done and the description.
     * Contains the deadline for Deadline tasks.
     * Contains the start time and end time for Event tasks.
     *
     * @param keyword Keyword used to filter tasks
     * @return ArrayList containing all added tasks with the keyword in their description
     */
    public ArrayList<Task> getTasksByKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.hasKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a provided tag to the task given its number in the list.
     *
     * @param taskNum Number of task in the list.
     * @param tag String representing tag to be added to the task.
     * @throws TaskUndoneException If task has not been done.
     */
    public Task addTagToTask(int taskNum, String tag) {
        Task currentTask = tasks.get(taskNum);
        currentTask.setTag(tag);
        return currentTask;
    }

    /**
     * Returns an ArrayList containing all tasks which have the specified tag,
     * including their type, whether it is done and the description.
     * Contains the deadline for Deadline tasks.
     * Contains the start time and end time for Event tasks.
     *
     * @param tag Tag added to tasks.
     * @return ArrayList containing all added tasks with the tag.
     */
    public ArrayList<Task> getTasksByTag(String tag) {
        return tasks.stream()
                .filter(task -> task.hasTag(tag))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
