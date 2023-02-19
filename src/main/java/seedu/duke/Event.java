package seedu.duke;

import java.util.HashMap;

/**
 * Represents a task that has a start date and an end date
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * @param parsed A hashmap containing the event name, from date, and to date
     * @throws DukeException Exception thrown when event name is empty
     */
    public Event(HashMap<String, String> parsed) throws DukeException {
        super(parsed.get("event"));
        from = parsed.get("/from");
        to = parsed.get("/to");
        abbreviation = 'E';
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
