/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

/**
 * Represents a todo task. A <code>Todo</code> object corresponds to a
 * description by a string.
 * e.g., <code>"read book"</code>
 */
public class Todo extends Task {

    /**
     * Constructor for the class.
     *
     * @param description The description of the Todos task.
     */
    public Todo(String description) {
        super(description.trim());
    }

    /**
     * Override the toString method to return the Todos task.
     *
     * @return The String of the Todos task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
