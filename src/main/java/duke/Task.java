package duke;

/**
 * The task class contains all the tasks that can be opreated by
 * users.
 */
public class Task {
    private String taskDescription;
    private boolean isDone;

    /**
     * Generate a <code>Task</code> object.
     * @param taskDescription The task description.
     */
    public Task (String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Get the status of a task.
     *
     * @return Whether the task is being marked or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Set the task as done.
     */
    public void setIsDone() {
        isDone = true;
    }

    /**
     * Set the task as not done.
     */
    public void revertIsDone() {
        isDone = false;
    }

    /**
     * Get the description of a task
     *
     * @return A specific task description.
     */
    public String getTaskDes() {
        return taskDescription;
    }

    /**
     * Construct display message.
     *
     * @return Message in format.
     */

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getTaskDes();
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
class Deadline extends Task {
    private TimeConvertor by;

    public Deadline(String description, TimeConvertor by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return this.by.getDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

class Event extends Task {
    private TimeConvertor from;
    private TimeConvertor to;

    public Event(String description, TimeConvertor from, TimeConvertor to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
