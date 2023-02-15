package spongebob.task;

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
    private static ArrayList<Events> eventTimeList = new ArrayList<>();
    private static final String STANDARD_FORMAT = "dd/MM/yyyy HH:mm";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(STANDARD_FORMAT);
    /**
     * Starting time of events in "dd/MM/yyyy HH:mm" format
     */
    private LocalDateTime formattedStartTime;
    /**
     * End time of events in "dd/MM/yyyy HH:mm" format
     */
    private LocalDateTime formattedEndTime;
    private String startTime;
    private String endTime;

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
            setUserInputTimeInChronologicalOrder(startTime, endTime);
            this.formattedStartTime = LocalDateTime.parse(this.startTime, FORMAT);
            this.formattedEndTime = LocalDateTime.parse(this.endTime, FORMAT);
            if (checkOverlappingEvents()) {
                throw new SpongebobEventOverlapException("Event overlap with previous event.");
            } else {
                eventTimeList.add(this);
            }
        } catch (DateTimeParseException e) {
            throw new SpongebobInvalidArgumentException(
                    String.format("The format of date-time is invalid.\nShould be %s", STANDARD_FORMAT));
        }
    }

    /**
     * Constructor to create an events task.
     *
     * @param description full description of event task, including its starting and ending time.
     */
    public Events(String[] description) {
        super(description[2]);
        setUserInputTimeInChronologicalOrder(description[3], description[4]);
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
        for (Events otherEvent: eventTimeList) {
            // let [x1, x2] and [y1, y2] be two interval
            // check overlap: !(x2 < y1 || x1 > y2) == x2 > y1 && x1 < y2
            if (formattedStartTime.isBefore(otherEvent.formattedEndTime)
                    && formattedEndTime.isAfter(otherEvent.formattedStartTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resets event time list everytime the program start its execution.
     *
     * @param eventTimeList list of event task that stored in the data file.
     */
    public static void setEventTimeList(ArrayList<Events> eventTimeList) {
        Events.eventTimeList = eventTimeList;
    }

    /**
     * Removes the event task from the event time list everytime the user delete event task.
     *
     * @param description description of task that stored in the file.
     */
    public static void deleteEventTimeList(String[] description) {
        Events event = new Events(description);
        eventTimeList.remove(event);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(), formattedStartTime.format(FORMAT), formattedEndTime.format(FORMAT));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Events) {
            Events e = (Events) obj;
            return getDescription().trim().equals(e.getDescription().trim())
                    && getStartTime().trim().equals(e.getStartTime().trim())
                    && getEndTime().trim().equals(e.getEndTime().trim());
        }
        return false;
    }
}
