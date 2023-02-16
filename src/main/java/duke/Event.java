package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an Event as a specific type of Task.
 */

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("MMM dd yyyy H:mm");

    /**
     * Creates an Event object.
     * @param description The description of the event.
     * @param from The date/time of the start of event.
     * @param to The date/time of the end of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws DukeException {
        super(description);
        this.to = to;
        this.from = from;

    }

    /**
     * Creates a String representation of the Event object to be stored in a list.
     * @return The string representation of the Event object in the list.
     */
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[E][X] %s (from: %s to: %s)", description, outputFormatDate(from),
                    outputFormatDate(to));
        }
        return String.format("[E][ ] %s (from: %s to: %s)", description, outputFormatDate(from), outputFormatDate(to));
    }

    /**
     * Creates a string representation of the Event object that is saved in a file.
     * @return The string representation of the Event object that is stored in a text file.
     */

    public String outputFormatDate(LocalDateTime date) {
        return date.format(PATTERN);
    }

    @Override
    public String sendOutputToFile() {
        return String.format("E | %d | %s | %s | %s" , isDone ? 1 : 0, description,
                outputFormatDate(from), outputFormatDate(to));
    }
}