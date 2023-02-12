package wessy.task;

/**
 * Task is an abstract base class that serves as the parent class for ToDo,
 * Deadline and Event classes. It abstracts the common fields like the task
 * description and the status of whether the task has been done.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Serves as a constructor for all the child classes to initialise. The task
     * description and the status of whether the task has been done need to be
     * specified.
     *
     * @param description The specified task description.
     * @param isDone The status of whether the task has been done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Serves as a constructor for all the child classes to initialise. Only the
     * task description needs to be specified.
     *
     * @param description The specified task description.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Represents the status of whether this Task has been done in a form of a
     * String, with "[X]" indicating done and "[ ]" indicating not done.
     *
     * @return An indication showing whether this Task has been done, in the
     * form of "[ ]".
     */
    String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Provides the common component of the String representation of all kinds
     * of Task, so that the respective child classes can use it to come up with
     * their String representation.
     *
     * @return The common component of the children's String representation.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Provides the common component for the child classes to converts into a
     * String representation that will be used while saving this task in a .txt
     * file to the Wessy's storage.
     *
     * @param separator A string that indicates the partition between the
     *      *                  different fields of a Task object.
     * @return The common component of the String representation used while
     * saving the task to the Wessy's storage
     */
    public String saveAsStr(String separator) {
        String mark = isDone ? "1" : "0";
        return separator + mark + separator + description;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks this task as "not done".
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Checks whether this task has been done.
     *
     * @return A boolean value indicating whether this task has been done.
     */
    public boolean checkIsDone() {
        return isDone;
    }
}
