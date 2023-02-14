package spongebob.task;

import javafx.util.Pair;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Event type of task.
 */
public class Events extends Task {
    /** Stores starting time and ending time of every event. */
    private static final ArrayList<Pair<LocalDateTime, LocalDateTime>> eventTimeList = new ArrayList<>();
    /**
     * Starting time of events in "dd/MM/yyyy HH:mm" format
     */
    private final LocalDateTime FORMATTED_STARTED_TIME;
    /**
     * End time of events in "dd/MM/yyyy HH:mm" format
     */
    private final LocalDateTime FORMATTED_END_TIME;
    private final String START_TIME;
    private final String END_TIME;
    private final String STANDARD_FORMAT = "dd/MM/yyyy HH:mm";
    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(STANDARD_FORMAT);

    /**
     * Constructor to create an event instance.
     *
     * @param description description of the task.
     * @param startTime starting time of events.
     * @param endTime end time of events.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public Events(String description, String startTime, String endTime) throws SpongebobInvalidArgumentException,
            SpongebobEventOverlapException {
        super(description);
        try {
            this.FORMATTED_STARTED_TIME = LocalDateTime.parse(startTime, FORMAT);
            this.FORMATTED_END_TIME = LocalDateTime.parse(endTime, FORMAT);
            this.START_TIME = startTime;
            this.END_TIME = endTime;
            if (isOverlapping()) {
                throw new SpongebobEventOverlapException("Event overlap with previous event.");
            } else {
                eventTimeList.add(new Pair<>(this.FORMATTED_STARTED_TIME, this.FORMATTED_END_TIME));
            }
        } catch (DateTimeParseException e) {
            throw new SpongebobInvalidArgumentException(
                    String.format("The format of date-time is invalid.\nShould be %s", STANDARD_FORMAT));
        }
    }

    public String getStartTime() {
        return START_TIME;
    }

    public String getEndTime() {
        return END_TIME;
    }

    /**
     * Checks whether scheduling anomalies occur between different events.
     *
     * @return true if anomalies scheduling, otherwise false.
     */
    private boolean isOverlapping() {
        for (Pair<LocalDateTime, LocalDateTime> otherEvent: eventTimeList) {
            if (!(FORMATTED_STARTED_TIME.isAfter(otherEvent.getValue())
                    || FORMATTED_END_TIME.isBefore(otherEvent.getKey()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(), FORMATTED_STARTED_TIME.format(FORMAT), FORMATTED_END_TIME.format(FORMAT));
    }
}
