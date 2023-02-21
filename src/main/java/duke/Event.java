package duke;

import java.time.LocalDate;

import duke.exceptions.DukeyException;


/**
 * Event is a type of Task which contains a name and two dates (in the form of
 * a LocalDate) indicating the starting date and the ending date.
 * completed.
 */
public class Event extends Task {
    private static final String TYPE = "[E]";
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Returns an Event with a name, start date and end date, and sets its status to undone.
     * @param name the name of the Event
     * @param start the starting date of the event
     * @param end the ending date of the event
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event with a name, start date and end date, and sets its status accordingly.
     * @param name the name of the Event
     * @param start the starting date of the event
     * @param end the ending date of the event
     * @param isMarked the status of the event
     */
    public Event(String name, LocalDate start, LocalDate end, boolean isMarked) {
        super(name, isMarked);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event created with details taken from user input.
     * @param ui object to handle user interactions
     * @return Event created based on the user's input
     * @throws DukeyException on invalid String provided for date
     */
    public static Event createEvent(Ui ui) throws DukeyException {
        String eventName = ui.readTaskName("Event");
        LocalDate eventStart = ui.readTime("Event start time");
        LocalDate eventEnd = ui.readTime("Event end time");
        return new Event(eventName, eventStart, eventEnd);
    }

    /**
     * Returns an Event created from its log string. This method "loads"
     * an Event from a save.
     * @param logStringArray an array of strings where each string is a component of the log string that
     *                       has been split up
     * @return Event created from its log string.
     */
    public static Event createEventFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        String startString = logStringArray[3];
        String endString = logStringArray[4];
        boolean isMarked = !logStringArray[1].equals("0");

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        try {
            start = DukeyTime.getDateFromString(startString);
            end = DukeyTime.getDateFromString(endString);
        } catch (DukeyException e) {
            e.getMessage();
        }
        return new Event(name, start, end, isMarked);
    }

    /**
     * Returns a message to be printed whenever an Event is added.
     * @return the message to be printed
     */
    @Override
    public String getMessageWhenAdded() {
        return "DukeyList just added a new event:";
    }

    /**
     * Returns the log string of an Event, which is a string containing details about the task. The log
     * string will be used to save the task locally when the task list is saved.
     * @return the log string of the Event
     */
    @Override
    public String getLogString() {
        return "E" + "," + getMarkedStatus() + "," + this.getName() + "," + this.start + "," + this.end;
    }

    /**
     * Returns the string representation of an Event
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (" + DukeyTime.dateToString(this.start)
                    + " to " + DukeyTime.dateToString(this.end) + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (" + DukeyTime.dateToString(this.start)
                + " to " + DukeyTime.dateToString(this.end) + ")";
    }



}
