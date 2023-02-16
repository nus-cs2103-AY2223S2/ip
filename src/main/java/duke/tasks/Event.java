package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Task, its a Task with a start duration and end duration.
 */
public class Event extends Task {
    private String eventSpan;

    /**
     * Event Constructor: Takes in and process event content and deadline, stores it.
     *
     * @param content Contains the typed input read from user, changes it into Event object.
     */
    public Event(String content) {
        super(content.substring(6).split("/")[0]);
        String[] strArr = content.split("/from");
        String[] eventTime = strArr[1].substring(1).split("/to");
        String eventStartTime = eventTime[0];
        String eventEndTime = eventTime[1].substring(1);
        if (eventStartTime.contains("/")) {
            String[] strArrStart = eventStartTime.split("/");
            eventStartTime = dateFormat(strArrStart);
        } if (eventEndTime.contains("/")) {
            String[] strArrEnd = eventEndTime.split("/");
            eventEndTime = dateFormat(strArrEnd);
        }
        this.eventSpan = "(" + "by: " + eventStartTime + " to: " + eventEndTime + ")";
    }

    /**
     * Formats the date input by user neatly.
     *
     * @param strArrDate Formats the user's date input into a neat date notation.
     */
    public String dateFormat(String[] strArrDate) {
        LocalDateTime dateTypeEvent;
        dateTypeEvent = LocalDateTime.of(Integer.parseInt(strArrDate[2].substring(0,4)),
                Integer.parseInt(strArrDate[1]), Integer.parseInt(strArrDate[0]),
                Integer.parseInt(strArrDate[2].substring(5,7)),
                Integer.parseInt(strArrDate[2].substring(7,9)));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
        return dtf.format(dateTypeEvent);
    }

    /**
     * Event constructor: Takes in a boolean also, allowing is "marked" var to be customized.
     */
    public Event(String content, boolean mark) {
        super(content.split("\\(")[0], mark);
        this.eventSpan = "(" + content.split("\\(")[1];
    }

    public String toString() {
        String sign = "";
        return ". [E][" + super.markSign(super.isMark) + "] " + super.content + this.eventSpan;
    }

    /**
     * Provides the String to be stored in duke.txt when program terminates.
     */
    public String printRecord() {
        return "[E]" + " [" + super.markSign(super.isMark) + "] " + super.content + this.eventSpan + "\n";
    }
}