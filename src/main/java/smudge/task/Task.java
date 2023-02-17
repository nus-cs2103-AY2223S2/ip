package smudge.task;

/**
 * Task class represent a possible task to be added
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * constructor method used in subclasses
     * task initialised will be marked as not done first
     * @param description the name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * getter method for name of task
     * @return name of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter method for status of task in string
     * @return status of task in string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * getter method for status of task in boolean
     * @return status of task in boolean
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * setter method to mark task status as done
     */
    public void markDone() {
        this.isDone = true;
    }
    /**
     * setter method to mark task status as not done
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * method to return task in string
     * @return task in string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

