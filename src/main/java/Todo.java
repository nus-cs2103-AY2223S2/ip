/**
 * Project name: Duke
 * Author: Tan Jun Da
 * Student Number: A0234893U
 *
 * This class is for the Todos Task added by the User.
 */

public class Todo extends Task {

    /**
     * Constructor for the class.
     * @param description The description of the Todos task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Override the toString method to return the Todos task.
     * @return The String of the Todos task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
