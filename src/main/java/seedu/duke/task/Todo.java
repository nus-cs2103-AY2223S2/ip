/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

import java.util.stream.Collectors;

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
        String message = "[T] " + super.toString();
        if (tags.size() != 0) {
            message += "\n";
            message += "#" + tags.stream().collect(Collectors.joining(" "));
        }
        return message;
    }
}
