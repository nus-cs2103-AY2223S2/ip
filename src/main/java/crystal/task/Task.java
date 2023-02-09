package crystal.task;

/**
 * Represents the abstract task class for all tasks.
 *
 */
public abstract class Task {

    protected String description;
    private boolean isDone;


    /**
     * Constructor for Task class.
     *
     * @param description Task description
     *
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     *  Returns a String X if isDone is true else
     *  returns an empty string
     *
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     *  Returns the description of the task
     *
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *  Returns the printed output format shown in the list
     *
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


    /**
     *  Returns the String output for when saving the list
     *
     */
    public String toPrint() {
        return this.toString();
    }



}
