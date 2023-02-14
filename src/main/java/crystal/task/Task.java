package crystal.task;

/**
 * Represents the abstract task class for all tasks.
 *
 */
public abstract class Task {

    protected String description;
    private boolean isDone;

    private boolean isPriority;

    private int num; //priority number


    /**
     * Constructor for Task class.
     *
     * @param description Task description
     *
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isPriority = false;
        this.num = 0;
    }

    public void setIsPriority(boolean isPriority) {
        this.isPriority = isPriority;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setPriorityNum(int num) {
        this.num = num;
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

    public String getPriorityLevel() {
        return (isPriority ? "priority level" : " ");
    }

    //@@author amoonguss1-reused
    //Reused from https://github.com/amoonguss1/ip/blob/master/src/main/java/Nerd/entities/TaskList.java
    // with minor modifications
    /**
     *  Returns the description of the task
     *
     */
    public String getDescription() {
        assert !this.description.equals("") : "Empty description!";
        return this.description;
    }

    /**
     *  Returns the printed output format shown in the list
     *
     */
    public String toString() {
        if (this.isPriority == true) {
            return "[" + this.getStatusIcon() + "] " + this.description + " "
                    + "(" + this.getPriorityLevel() + " " + this.num + ")";
        } else {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }

    }


    /**
     *  Returns the String output for when saving the list
     *
     */
    public String toPrint() {
        return this.toString();
    }

}
