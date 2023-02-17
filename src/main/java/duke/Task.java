package duke;

/**
 * The task class contains all the tasks that can be operated by users.
 */
public class Task {
    private final String taskDescription;
    private boolean isDone;

    /**
     * Generates a <code>Task</code> object.
     *
     * @param taskDescription The task description.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Gets the status of a task.
     *
     * @return Whether the task is being marked or not.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Sets the task as done.
     */
    public void setIsDone() {
        isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void revertIsDone() {
        isDone = false;
    }

    /**
     * Gets the description of a task.
     *
     * @return A specific task description.
     */
    public String getTaskDes() {
        return taskDescription;
    }

    /**
     * Constructs display message.
     *
     * @return Message in format.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + taskDescription;
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
class Deadline extends Task implements Comparable<Deadline> {
    private final TimeConvertor by;

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

    @Override
    public int compareTo(Deadline o) {
        return by.compareTo(o.by);
    }
}

class Event extends Task implements Comparable<Event> {
    private final TimeConvertor from;
    private final TimeConvertor to;

    public Event(String description, TimeConvertor from, TimeConvertor to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public int compareTo(Event o) {
        return from.compareTo(o.from);
    }
}
