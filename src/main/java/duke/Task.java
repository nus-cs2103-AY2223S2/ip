package duke;

import java.time.LocalDateTime;

/**
 * Abstract class which specifies the structure of a Task.
 * <p>
 * Subclasses of <b>Task</b> are:
 * <li>ToDo
 * <li> DeadlineTask
 * <li>Event </p>
 * @author Merrick
 */
public abstract class Task implements Snoozable {
    protected String taskType = "T";
    protected LocalDateTime deadline = LocalDateTime.now();
    private final String taskName;
    private boolean isCompleted = false;
    /**
     * Constructor for Task class.
     *
     * @param taskName Description of task.
     * @param isCompleted Specifies if the task is isCompleted.
     */
    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Constructor for Task class
     * @param taskName Description of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks a task as done and prints out action done.
     * @param isCompleted Completion status of the task.
     */
    public String setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
        if (isCompleted) {
            return String.format("    Nice! I've marked this task as done:\n" + "     " + this);
        } else {
            return String.format("    OK, I've marked this task as not done yet:\n" + "       " + this);
        }
    }

    /**
     * Outputs the type of task.
     * @return String representing the type of the Task
     */
    public String displayType() {
        return String.format("[%s]", this.taskType);
    }

    /**
     * Outputs completion status of task
     * @return String represented the completion status of the Task.
     */
    public String displayMark() {
        if (this.isCompleted) {
            return "X";
        }
        return " ";
    }

    /**
     * Checks if the task description contains the specified keyword.
     * @param keyword Phrase to match with our Task.
     * @return Boolean indicating if the keyword is found in the task description.
     */
    public boolean containsKeyword(String keyword) {
        return this.taskName.contains(keyword);
    }

    /**
     * Returns the Task string to be stored in a .txt file for saving.
     * @return String to be saved in Task history.
     */
    public String saveTaskString() {
        return String.format("%s|%s|%b", this.taskType, this.taskName, this.isCompleted);
    }

    @Override
    public String toString() {
        return String.format("  %s[%s] %s", this.displayType(), this.displayMark(), this.taskName);
    }
}
