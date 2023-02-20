package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * An Event represents a type of task that can be added by the user. It has both a start and end date/time
 */

public class Event extends Task {
    String startDayTime;
    String endDayTime;

    String deadlineString;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Constructor for an event, which has both a start date/time and an end date/time
     * @param startTime A String containing both the starting date and time of the event
     * @param endTime A String containing both the ending date and time of the event
     * @param description A brief description of the event
     */

    public Event(String startTime, String endTime, String description) {
        super(description);
        String[] startComponents = startTime.strip().split(" ");
        String[] endComponents = endTime.strip().split(" ");
        this.startDayTime = startTime;
        this.endDayTime = endTime;
        try {
            this.startDate = LocalDate.parse(startComponents[0].strip(), formatter);
            this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
            this.endDate = LocalDate.parse(endComponents[0].strip(), formatter);
            this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
        } catch (DateTimeParseException e) {
            try {
                this.startDate = LocalDate.parse(startComponents[0].strip(), formatter2);
                this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
                this.endDate = LocalDate.parse(endComponents[0].strip(), formatter2);
                this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
            } catch (DateTimeParseException e2) {
                System.out.println("Please enter the date in this format: dd-MMM-YYYY OR dd/MMM/YYYY");
                System.out.println("Please enter the time in this format: HHmm");
            }
        }
    }

    @Override
    public String toString() {
        String formattedStartDate = startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return (isDone? "[E][X] " : "[E][ ] ") + description + ". From: " + formattedStartDate + " @ " +
                this.startTime.toString() + " hrs" +
                ". To: " + formattedEndDate + " @ " + this.endTime.toString() + " hrs";
    }
}
