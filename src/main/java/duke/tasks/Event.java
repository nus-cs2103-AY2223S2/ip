package duke.tasks;

import duke.exceptions.InvalidEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task.
 * Tag = E.
 */
public class Event extends Task {

    private String tag = "E";
    private LocalDateTime from;
    private LocalDateTime to;

    public Event() {
        super.tag = tag;
    }

    /**
     * Constructor for initialising of an event task.
     * @param description The description of the event task.
     */
    public Event(String description) {
        super.tag = tag;
        super.description = description;
    }

    //KEEP ALL WORDS SEPARATED BY SPACES
    @Override
    public void formatDescription(String input) throws InvalidEvent {
        String dscp = input.replace("event ", "");
        if (dscp.isBlank()) {
            throw new InvalidEvent();
        }

        int fromId = dscp.indexOf("/from");
        int toId = dscp.indexOf(("/to"));
        if (fromId == -1 || toId == -1) {
            throw new InvalidEvent();
        }

        try {
            this.from = LocalDateTime.parse(input.substring(fromId + 6, toId - 1),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(input.substring(toId + 4),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
        } catch (DateTimeParseException e) {
            throw new InvalidEvent("Please enter the event period correctly");
        }

        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        String description = String.format("%s (from: %s to: %s)", input.substring(0, fromId - 1),
                formattedFrom, formattedTo);
        super.description = description;
    }
}
