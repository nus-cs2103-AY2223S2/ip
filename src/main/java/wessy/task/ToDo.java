package wessy.task;

/**
 * This ToDo class inherits from its parent class, Task, and it encapsulates
 * the information and operations required while handling a "todo" task.
 */
public class ToDo extends Task {
    /**
     * Constructs an instance of ToDo by specifying the task description and
     * whether the task has been done.
     *
     * @param description The specified task description.
     * @param isDone The status of whether the task has been done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs an instance of ToDo by specifying the task description. The
     * status of this ToDo is by default set as not done when initialised.
     *
     * @param description The specified task description.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Overrides the toString method of the parent class, Task. Returns the
     * String representation of this ToDo object by adding "[T]" in front of the
     * String representation returned by the parent's toString method.
     *
     * @return The String representation of this ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts this ToDo object into a String representation that will be
     * used while saving this task in a .txt file to the Wessy's storage.
     *
     * @param separator A string that indicates the partition between the
     *                  different fields of a ToDo object.
     * @return A String representation that will be used while saving this task
     * to the Wessy's storage.
     */
    @Override
    public String saveAsStr(String separator) {
        return "T" + super.saveAsStr(separator) + "\n";
    }
}
