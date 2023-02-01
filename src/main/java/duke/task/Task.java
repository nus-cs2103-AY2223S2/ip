package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A task stores its description and on whether is it done or not. The task can be marked as done or unmarked as not
 * done.
 */
public class Task {

    /**
     * An empty task.
     */
    public static final Task EMPTY_TASK = new Task("");
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * Tracks whether the task is done or not.
     */
    protected boolean isDone;

    /**
     * Constructor for a new task with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructor for a task with the provided description and its status of completion.
     *
     * @param description The description of the task
     * @param isDone      Whether the task is done or not
     */
    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Checks the completion status of the task and returns a string representation of it.
     *
     * @return String representation of completion status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Unmark the task as not done.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Provides a string representation of the task to be displayed by the chatbot.
     *
     * @return String representation of task to be displayed
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Checks the current task on whether it is an empty task.
     *
     * @return Boolean on whether it is an empty task
     */
    public boolean isEmpty() {
        return equals(EMPTY_TASK);
    }

    /**
     * Return string representation of the marked status
     *
     * @return String representation of marked status
     */
    public String getMarkedStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Creates a string representation of the task so that it can be imported in the future.
     *
     * @return String representation of task to be stored for future imports
     */
    public String toData() {
        return String.format("Task | marked: %s ; description: %s", getMarkedStatus(), description);
    }

    /**
     * Checks on whether the string representation of the task is the same format as the one exported. If it is, then a
     * new task is created with the described properties. Otherwise, return an empty task.
     *
     * @param data String representation of a task
     * @return A task object that describes the given data of the task
     */
    public static Task readFromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1");
            String description = matcher.group(4);
            return new Task(description, isMarked);
        }
        return Task.EMPTY_TASK;
    }

    public boolean isInDescription(String term) {
        return description.contains(term);
    }
}

