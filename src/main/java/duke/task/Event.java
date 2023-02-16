package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Event extends Task{
    protected Date eventStart;
    protected Date eventEnd;

    protected String startString;
    protected String endString;

    /**
     * constructor of the event class
     * @param item is the task name
     * @param type is the type of task
     * @param start is a Date object indicating the starting time of the event
     * @param end is a Date object indicating the ending time of the event
     * @param startStringVer is the same as date but stores the date as string
     * @param endStringVer is the same as date but stores the date as string
     */
    public Event(String item, String type, Date start, Date end, String startStringVer, String endStringVer) {
        super(item,type);
        eventStart = start;
        eventEnd = end;
        startString = startStringVer;
        endString = endStringVer;
    }
    @Override
    public String toString() {
        SimpleDateFormat convertToString = new SimpleDateFormat("dd MMM yyyy HH:mm a", Locale.ENGLISH);
        return "[E]" + super.toString() + " (From: " + convertToString.format(eventStart) + " To: "
                + convertToString.format(eventEnd) + ") " + containNotes();
    }

    /**
     * gets the time of the event
     * @return the timing of the event in String form
     */
    @Override
    public String getTime() {
        return startString + "-" + endString;
    }
}
