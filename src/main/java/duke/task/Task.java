package duke.task;

/**
 * Handles Task
 * Super class of Deadline, Event, and ToDo
 */
public class Task {
    protected  String description;
    protected boolean isDone;

    /**
     * Constructor of Task
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Determines whether the task is done or not
     * @return String about whether the task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     *
     * @return String of status icon and description of task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Changes protected boolean isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Changes protected boolean isDone to false
     */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }
}
