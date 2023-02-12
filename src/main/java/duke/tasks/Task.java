package duke.tasks;

/**
 * Parent Task object that represents each task inputted by the user.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T

 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for creating a Task object.
     *
     * @param description name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     *
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     *
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of a Task object which users can see in the command line.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.isDone ? "X" : " ", this.description);
    }

    /**
     * Returns string representation of a task object to store in the duke.functions.Duke.txt file.
     *
     * @return String representation of task.
     */
    public String toStringDb() {
        return String.format(
                "%s|%s",
                this.isDone ? "X" : 0,
                this.description
        );
    }
}
