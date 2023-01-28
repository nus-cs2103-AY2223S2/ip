package duke.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.tasks.ToDo;

/**
 * Contains the list of all tasks logged in by the user.
 */
public class TaskList {

    private ArrayList<Task> array;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> array) {
        this.array = array;
    }

    public ArrayList<Task> getArray() {
        return this.array;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks in the TaskList.
     */
    public int size() {
        return this.array.size();
    }

    /**
     * Returns the task at the specified index, within the TaskList.
     *
     * @param taskIndex an integer referencing the index of a task.
     * @return the task at the specified index.
     */
    public Task getTask(int taskIndex) {
        return this.array.get(taskIndex);
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param title the title of the task.
     * @param type the type of the task.
     * @param start the start date and time of the task, if applicable.
     * @param end the end date and time of the task, if applicable.
     * @param done the completion status of the task.
     * @param shouldPrintOutput a boolean specifying if the function should print
     *     an output as acknowledgement for adding the task to the TaskList.
     */
    public void addToList(String title, TaskType type, LocalDateTime start,
                          LocalDateTime end, boolean done, boolean shouldPrintOutput) {
        // TODO: Create multiple addToLists with different method signature
        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(title, done);
        } else if (type == TaskType.DEADLINE) {
            task = new Deadline(title, end, done);
        } else if (type == TaskType.EVENT) {
            task = new Event(title, start, end, done);
        } else {
            Ui.println("Something seems wrong...");
            return;
        }
        this.array.add(task);
        if (shouldPrintOutput) {
            Ui.println("Added this to your task list:");
            Ui.println("  " + task.toString());
            Ui.println(String.format("Number of tasks left: %d", this.array.size()));
        }
    }

    /**
     * Deletes the task at the specified index, within the TaskList.
     *
     * @param taskIndex an integer referencing the index of a task.
     */
    public void deleteTask(int taskIndex) {
        Task deletedTask = this.array.remove(taskIndex);
        Ui.println("Removed this from your task list:");
        Ui.println("  " + deletedTask.toString());
        Ui.println(String.format("Number of tasks left: %d", this.array.size()));
    }

    /**
     * Changes the completion status of the task at the specified index, within the TaskList.
     *
     * @param taskIndex an integer referencing the index of a task.
     * @param completionStatus a boolean specifying whether the task is completed or not.
     */
    public void changeTaskCompletionStatus(int taskIndex, boolean completionStatus) {
        Task task = this.array.get(taskIndex);
        task.setDone(completionStatus);
        if (completionStatus) {
            Ui.println("Solid work man! This task is marked done");
        } else {
            Ui.println("Aww what happened? This task is marked as undone");
        }
        String toPrint = task.toString();
        Ui.println(toPrint);
    }

}
