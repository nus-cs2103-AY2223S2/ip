package duke;

import java.time.LocalDate;

/**
 * Represents a task of event type
 */

public class Event extends Task {

    /**
     * initialises an event object with the given description
     * 
     * @param description
     *            description of event
     */

    public Event(String description) {
        super(description);

    }

    /**
     * initialises an event object with the given description and deadline
     * 
     * @param description description of event
     * @param deadline deadline of event
     */

    public Event(String description, LocalDate deadline) {
        super(description, deadline);

    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String getTaskFileSaveFormat() {
        return "[E]###" + super.getTaskFileSaveFormat();
    }
}
