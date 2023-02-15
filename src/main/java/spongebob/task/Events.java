package spongebob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;

/**
 * Event type of task.
 */
public class Events extends Task {
    /** Stores starting time and ending time of every event. */
    private static ArrayList<Events> EVENT_LIST = new ArrayList<>();
    private static final String STANDARD_FORMAT = "dd/MM/yyyy HH:mm";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(STANDARD_FORMAT);
    /**
     * Starting time of events in "dd/MM/yyyy HH:mm" format
     */
    private final LocalDateTime FORMATTED_START_TIME;
    /**
     * End time of events in "dd/MM/yyyy HH:mm" format
     */
    private final LocalDateTime FORMATTED_END_TIME;
    private String startTime;
    private String endTime;

    /**
     * Constructor to create an event instance.
     *
     * @param description description of the task.
     * @param startTime starting time of events.
     * @param endTime end time of events.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     * @throws SpongebobEventOverlapException indicate there are overlapping tasks exist.
     */
    public Events(String description, String startTime, String endTime) throws SpongebobInvalidArgumentException,
            SpongebobEventOverlapException {
        super(description);
        try {
            setUserInputTimeInChronologicalOrder(startTime, endTime);
            this.FORMATTED_START_TIME = LocalDateTime.parse(this.startTime, FORMAT);
            this.FORMATTED_END_TIME = LocalDateTime.parse(this.endTime, FORMAT);
            if (checkOverlappingEvents()) {
                throw new SpongebobEventOverlapException("Event overlap with previous event.");
            } else {
                EVENT_LIST.add(this);
            }
        } catch (DateTimeParseException e) {
            throw new SpongebobInvalidArgumentException(
                    String.format("The format of date-time is invalid.\nShould be %s", STANDARD_FORMAT));
        }
    }

    private void setUserInputTimeInChronologicalOrder(String... time) {
        if (LocalDateTime.parse(time[0], FORMAT).isBefore(LocalDateTime.parse(time[1], FORMAT))) {
            this.startTime = time[0];
            this.endTime = time[1];
        } else {
            this.startTime = time[1];
            this.endTime = time[0];
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    // Checks whether scheduling anomalies occur between different events.
    private boolean checkOverlappingEvents() {
        for (Events otherEvent: EVENT_LIST) {
            // let [x1, x2] and [y1, y2] be two interval
            // check overlap: !(x2 < y1 || x1 > y2) == x2 > y1 && x1 < y2
            if (FORMATTED_START_TIME.isBefore(otherEvent.FORMATTED_END_TIME)
                    && FORMATTED_END_TIME.isAfter(otherEvent.FORMATTED_START_TIME)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resets event time list everytime the program start its execution.
     *
     * @param eventList list of event task that stored in the data file.
     */
    public static void setEventList(ArrayList<Events> eventList) {
        EVENT_LIST = eventList;
    }

    /**
     * Removes the event task from the event time list everytime the user delete event task.
     *
     * @param t event that deleted from the file.
     */
    public static void deleteEventTimeList(Events t) {
        EVENT_LIST.remove(t);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(), FORMATTED_START_TIME.format(FORMAT), FORMATTED_END_TIME.format(FORMAT));
    }
}
