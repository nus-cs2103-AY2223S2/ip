package saturday.models;

/**
 * The ToDo class is a model class that represents a task of type "To Do". It extends the Task class and
 * provides a specific implementation of the toString() method.
 *
 * @author Titus Lowe
 * @version 0.1
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo with the specified description.
     *
     * @param description the description of the task
     */
    public ToDo(int index, String description) {
        super(index, description);
    }

    /**
     * Returns a string representation of the object.
     * The format of the returned string is "[T]" followed by the result of the toString() method from the superclass.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
