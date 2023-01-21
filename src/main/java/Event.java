/**
 * Project name: Duke
 * Author: Tan Jun Da
 * Student Number: A0234893U
 *
 * This class is for the Events Task added by the User.
 */

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for the class Event.
     * @param description The description of the Event object.
     * @param from The start timeline of the Event.
     * @param to The end timeline of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }

    /**
     * Override the toString method to return the Event task.
     * @return The String of the Event task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + " )";
    }
}
