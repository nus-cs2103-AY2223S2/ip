/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

import java.util.stream.Collectors;

/**
 * Represents an event task. An <code>Event</code> object corresponds to a
 * description and deadline (from and to) by three strings.
 * e.g., <code>"read book","Monday 2pm","3pm"</code>
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
        super(description.trim());
        this.from = from.trim();
        this.to = to.trim();
    }

    /**
     * Getter for the variable from of this object instance.
     *
     * @return The string of the variable from.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Getter for the variable to of this object instance.
     *
     * @return The string of the variable to.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Override the toString method to return the Event task.
     *
     * @return The String of the Event task.
     */
    @Override
    public String toString() {
        String message = "[E] " + super.toString()
                + " (from: " + from + " to: " + to + " )";
        if (tags.size() != 0) {
            message += "\n";
            message += "#" + tags.stream().collect(Collectors.joining(" "));
        }
        return message;
    }
}
