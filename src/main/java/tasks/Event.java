package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * A Event class that is a blueprint for an event
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor to instantiate a tasks.Event object
     * @param description of tasks.Event
     * @param from in the format of a string in the format of day/month/year time eg. 02/05/2019 1800
     * @param to in the format of a string in the format of day/month/year time eg. 02/05/2019 1800
     */


    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * gets the time from and to appended
     * @return String
     */
    public String getTime() {
        return from + " - " + to;
    }

    /**
     * Converts a string into formatted version
     * @param str in the format of dd/MM/yyyy  HHmm eg. 02/12/2022 1800
     * @return a string formatted eg. 2nd December 2022 6:00 PM
     */
    public String dateCovert(String str) {
        // String to LocalDate
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(str, format1);
        // DateTime to String
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
        return formattedDate;
    }

    /**
     * Returns the string representation of the Event object.
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateCovert(from) + " to: " + dateCovert(to) + ")";
    }
}
