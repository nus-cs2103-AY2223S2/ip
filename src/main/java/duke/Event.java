package duke;

/**
 * Encapsulates an Event as a specific type of Task.
 */

public class Event extends Task {
    private String timeOfEvent;

    /**
     * Creates an Event object.
     * @param description The description of the event.
     * @param timeOfEvent The date/time of the event.
     */
    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    /**
     * Creates a String representation of the Event object to be stored in a list.
     * @return The string representation of the Event object in the list.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent + ")";
    }

    /**
     * Creates a string representation of the Event object that is saved in a file.
     * @return The string representation of the Event object that is stored in a text file.
     */
    @Override
    public String sendOutputToFile() {
        return String.format("E | %d | %s | %s" , isDone ? 1 : 0, description, timeOfEvent);
    }
}