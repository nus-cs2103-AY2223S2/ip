package duke.task;

import duke.TaskType;

/**
 * Abstract Task class that is extended by ToDo, Deadline and Event class.
 */
public abstract class Task {
    private TaskType taskType;
    private String description;
    private boolean isDone;

    /**
     * Task class constructor.
     * @param taskType Type of task.
     * @param description Description of task.
     */
    public Task(TaskType taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = false;
    }

    public abstract String taskToSavedForm();

    /**
     * Static method that creates ToDo, Deadline or Event object depending on command in string.
     * @param str Command inputted by user.
     * @return Task object that could be a ToDo, Deadline or Event object.
     */
    public static Task makeTask(String str) {
        if (str.startsWith("todo ")) {
            return ToDo.to(str.substring(5));
        } else if (str.startsWith("deadline ")) {
            return Deadline.to(str.substring(9));
        } else {
            return Event.to(str.substring(6));
        }
    }

    /**
     * Get description of task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get status icon to mark whether a task is done or not for string representation of task.
     * @return X if task is done or just a blank space if task is not.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Mark Task object as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark task as undone.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * String representation of Task object.
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        String str = "[" + this.taskType + "]";
        str += "[" + this.getStatusIcon() + "] ";
        str += this.description;
        return str;
    }
}
