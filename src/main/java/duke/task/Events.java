package duke.task;

import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Event type of task.
 */
public class Events extends Task {
    /**
     * Starting time of events in "YYYY-MM-DDTHH:MM:SS" format
     */
    private LocalDateTime formattedStartTime;
    /**
     * End time of events in "YYYY-MM-DDTHH:MM:SS" format
     */
    private LocalDateTime formattedEndTime;
    private String startTime;
    private String endTime;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    /** Store starting time and ending time of each event. */
    private static ArrayList<Pair<LocalDateTime,LocalDateTime>> eventTimeList = new ArrayList<>();

    /**
     * Constructor to create an event instance.
     *
     * @param description description of the task.
     * @param formattedStartTime starting time of events.
     * @param end end time of events.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public Events(String description, String startTime, String endTime) throws DukeInvalidArgumentException,
            DukeEventOverlapException {
        super(description);
        try {
            this.formattedStartTime = LocalDateTime.parse(startTime);
            this.formattedEndTime = LocalDateTime.parse(endTime);
            this.startTime = startTime;
            this.endTime = endTime;
            if (isOverlapping()) {
                throw new DukeEventOverlapException("Event overlap with previous event.");
            } else {
                eventTimeList.add(new Pair<>(this.formattedStartTime, this.formattedEndTime));
            }
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("The format of date-time is invalid.");
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    /**
     * Checks whether scheduling anomalies occur between different events.
     *
     * @return true if anomalies scheduling, otherwise false.
     */
    private boolean isOverlapping() {
        for (Pair<LocalDateTime,LocalDateTime> otherEvent: eventTimeList) {
            if (!(formattedStartTime.isAfter(otherEvent.getValue()) || formattedEndTime.isBefore(otherEvent.getKey()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return string representation of events task.
     *
     * @return string representation of event task.
     */
    public String toString() {
        return String.format("[E] %s (from %s to: %s)",
                super.toString(), formattedStartTime.format(format), formattedEndTime.format(format));
    }
}
