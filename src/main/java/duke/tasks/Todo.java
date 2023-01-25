package duke.tasks;
/**
 * A type of Task that only contains a description
 */
public class Todo extends Task{

    /**
     * Constructor for Todo class
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Another constructor for Todo class
     *
     * @param description the description of the task
     * @param isDone whether the task should be marked upon creation
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /** Returns the string representation of Todo
     *
     * @return string representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /** Returns the string representation of Todo in data format
     *
     * @return string representation of Todo in data format
     */
    @Override
    public String toDataFormatString() {
        int marked = 0;
        if(super.isDone) {
            marked = 1;
        }
        return "T / " + marked + " / " + super.description;
    }

    /**
     * Checks if the given Object is the same as this
     *
     * @param o the Object being compared
     * @return true if o is an instance of this and have the same description
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }

        Todo t = (Todo) o;
        if (this.description.equals(t.description)) {
            return true;
        } else {
            return false;
        }
    }
}
