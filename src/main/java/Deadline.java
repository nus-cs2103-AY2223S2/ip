/**
 * Project name: Duke
 * Author: Tan Jun Da
 * Student Number: A0234893U
 *
 * This class is for the Deadlines Task added by the User.
 */

public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for the class Deadline.
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     */
    public Deadline(String description, String by){
        super(description);
        this.by = by.trim();
    }

    /**
     * Override the toString method to return the Deadline task.
     * @return The String of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + " )";
    }
}
