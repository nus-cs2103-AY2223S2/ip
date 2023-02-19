package fideline.task;


/**
 * Representation of a task that can be completed.
 *
 * @author Fun Leon
 */
public abstract class Task {

    static final char DONE = 'X';

    static final char UNDONE = ' ';


    /** Title describing the task */
    private String description;


    /** Status that indicates if the task is done */
    private boolean isDone;


    /**
     * Constructs a task object that has a description. Tasks
     * are not done by default.
     *
     * @param description Title given to the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }


    /**
     * Returns string representation of the task.
     *
     * @return String representing the task.
     */
    public String toString() {
        char statusString = isDone ? DONE : UNDONE;
        String stringFormat = "[%c] %s";
        return String.format(stringFormat, statusString, description);
    }


    /**
     * Changes status of the task to done.
     */
    public void mark() {
        isDone = true;
    }


    /**
     * Changes status of the task to not done.
     */
    public void unmark() {
        isDone = false;
    }

    public boolean isMarked() {
        return isDone;
    }


    /**
     * Returns description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    public String getStorageString() {
        char markStatus = isDone ? DONE : UNDONE;
        String stringFormat = "%s|%s";
        return String.format(stringFormat, markStatus, description);
    }

    public boolean equals(Task task) {
        boolean isSameDescription = (task.getDescription().equals(description));
        boolean isSameStatus = task.isMarked() == isDone;
        return isSameDescription && isSameStatus;
    }


}
