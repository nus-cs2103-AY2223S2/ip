package duke.task;

import duke.util.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event that is a Task.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEEE, "
            + "MMMM d yyyy, h:mm a");
    private String[] period;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructor for Event.
     * @param description Description of Event.
     * @param period Event period.
     */
    public Event(String description, String[] period) {
        super(description);
        this.period = period;
        this.fromDateTime = LocalDateTime.parse(period[0]);
        this.toDateTime = LocalDateTime.parse(period[1]);
    }

    /**
     * Translates the task into data format.
     * @return A String that represents the task in data format.
     */
    public String toData() {
        return String.format("E | %s | %s | %s to %s", this.getStatusIcon(),
                this.getDescription(), this.period[0], this.period[1]);
    }

    /**
     * Check if Event details are valid.
     * @param eventDetails The details of Event task that are entered by user.
     * @return A String array containing the details that are required by Event.
     * @throws DukeException If there are missing details for Event.
     */
    public static String[] checkEventDetails(String eventDetails) throws DukeException {
        if (eventDetails.isBlank()) {
            throw new DukeException("You did not enter the required details for your task!");
        }
        if (!eventDetails.contains("/from") || !eventDetails.contains("/to")) {
            throw new DukeException("You have entered a wrong format for this command :(\n"
                    + "Type 'help' for a list of commands and their respective formats.");
        }
        String eventDescription;
        try {
            eventDescription = eventDetails.split(" /from ", 2)[0];
            // If a description is not entered, index 1 will be empty.
            String period = eventDetails.split(" /from ", 2)[1];
            if (eventDescription.isBlank()) {
                throw new DukeException("You did not enter a description for your task!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a description for your task!");
        }
        eventDetails = eventDetails.split(" /from ", 2)[1];
        String fromDate;
        try {
            fromDate = eventDetails.split(" /to ", 2)[0];
            // If a start date or end date is not entered, index 1 will be empty.
            String toDate = eventDetails.split(" /to ", 2)[1];
            if (fromDate.isBlank()) {
                throw new DukeException("You did not enter a date which your task starts!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a start date and an end date for your task!");
        }
        String toDate;

        toDate = eventDetails.split(" /to ", 2)[1];
        if (toDate.isBlank()) {
            throw new DukeException("You did not enter a date which your task ends!");
        }

        return new String[] {eventDescription, toDate, fromDate};
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (From: %s, To: %s)", this.getStatusIcon(), this.getDescription(),
                this.fromDateTime.format(DATE_TIME_FORMATTER), this.toDateTime.format(DATE_TIME_FORMATTER));
    }
}
