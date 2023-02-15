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

    public void addToList(Task task) {
        this.array.add(task);
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param title the title of the task.
     * @param type the type of the task.
     * @param start the start date and time of the task, if applicable.
     * @param end the end date and time of the task, if applicable.
     * @param isDone the completion status of the task.
     * @param shouldPrintOutput a boolean specifying if the function should print
     *     an output as acknowledgement for adding the task to the TaskList.
     */
    public String addToList(String title, TaskType type, LocalDateTime start,
                          LocalDateTime end, boolean isDone, boolean shouldPrintOutput) {
        // TODO: Create multiple addToLists with different method signature
        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(title, isDone);
        } else if (type == TaskType.DEADLINE) {
            task = new Deadline(title, end, isDone);
        } else if (type == TaskType.EVENT) {
            task = new Event(title, start, end, isDone);
        } else {
            // TODO: Think of how to show the user this issue
            return "Something seems wrong with the task type...I could not add this task";
        }
        this.array.add(task);
        String resultString = "Added this to your task list: \n"
                + task.toString()
                + "\n"
                + String.format("Number of tasks left: %d", this.array.size())
                + "\n";

        return resultString;
    }

    /**
     * Deletes the task at the specified index, within the TaskList.
     *
     * @param taskIndex an integer referencing the index of a task.
     */
    public String deleteTask(int taskIndex) {
        Task deletedTask = this.array.remove(taskIndex);
        String resultString = "Removed this from your task list: "
                + "\n"
                + deletedTask.toString()
                + "\n"
                + String.format("Number of tasks left: %d", this.array.size());
        return resultString;
    }

    /**
     * Changes the completion status of the task at the specified index, within the TaskList.
     *
     * @param taskIndex an integer referencing the index of a task.
     * @param isDone a boolean specifying whether the task is completed or not.
     */
    public String changeTaskCompletionStatus(int taskIndex, boolean isDone) {
        Task task = this.array.get(taskIndex);
        task.setDone(isDone);
        String resultString = "";
        if (isDone) {
            resultString += "Solid work man! This task is marked done\n";
        } else {
            resultString += "Aww what happened? This task is marked as undone\n";
        }
        resultString += task.toString() + "\n";
        return resultString;
    }

    /**
     * Returns the string representation of all the tasks related to the keyword specified.
     *
     * @param keyword a keyword to search all tasks.
     * @return a String representing all the results of the search.
     */
    public String find(String keyword) {
        TaskList result = new TaskList();
        for (Task t: this.array) {
            String taskDescription = t.toString();
            boolean containsKeyword = taskDescription.contains(keyword);
            if (containsKeyword) {
                result.addToList(t);
            }
        }

        String resultString = "";
        if (result.size() == 0) {
            resultString = "There are no matching tasks in your list!";
            return resultString;
        }
        assert result.size() > 0 : "Number of results from search cannot be negative.";
        resultString = "Here are the matching tasks in your list:";
        resultString += ReplyString.getTaskListString(result);
        return resultString;
    }
}
