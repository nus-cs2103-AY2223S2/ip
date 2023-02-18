package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Represents a Event task in Duke.
 */
public class Event extends Task {
    private static final String TYPE_TO_STRING = "E";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Represents a Event task in Duke.
     *
     * @param task task details.
     * @param startDateTime start date and time of the event.
     * @param endDateTime end date and time of the event.
     * @throws DateTimeParseException thrown when either of the date and/or time is not parsable.
     */
    public Event(String task, String startDateTime, String endDateTime) throws DateTimeParseException {
        super(task);
        this.setType(Types.EVENT);

        this.startDateTime = LocalDateTime.parse(startDateTime, FORMATTER);
        this.endDateTime = LocalDateTime.parse(endDateTime, FORMATTER);
    }

    /**
     * Represents an Event task in Duke.
     *
     * @param data an array of Strings with relevant information typically obtained from the database in Duke.
     */
    public Event(String[] data) {
        super(data[2]);
        this.setCompleted(Objects.equals(data[1], "X"));
        this.startDateTime = LocalDateTime.parse(data[3]);
        this.endDateTime = LocalDateTime.parse(data[4]);
    }

    /**
     * @return the status of the Event task with its time formatted.
     */
    @Override
    public String getStatus() {

        String status = this.getCompleted() ? "[X] " : "[ ] ";
        return "[" + TYPE_TO_STRING + "]" + status + this.getDetails()
                + " (from: " + this.startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " ["
                + this.startDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")" + " to: "
                + this.endDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " ["
                + this.endDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")" + ")";
    }

    /**
     * @return all relevant information of the Event task in an ArrayList of Strings to be saved into the Database.
     */
    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(TYPE_TO_STRING);
        data.add(this.getCompleted() ? "X" : " ");
        data.add(this.getDetails());
        data.add(this.startDateTime.toString());
        data.add(this.endDateTime.toString());
        return data;
    }
}
