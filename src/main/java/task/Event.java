package task;

import exception.InvalidCommandInputException;
import exception.InvalidDateFormatException;

import helper.DateTimeHelper;

import java.time.LocalDateTime;

/**
 * Represents an event task that lasts between two time periods.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for an Event task.
     * @param content The task to be done.
     * @param startString Start date of event.
     * @param endString End date of event.
     * @throws InvalidDateFormatException If date field in content is in the incorrect format.
     */
    Event(String content, String startString, String endString) throws InvalidDateFormatException {
        super(content);
        this.start = DateTimeHelper.parse(startString);
        this.end = DateTimeHelper.parse(endString);
    }

    /**
     * Constructor for an Event task.
     * @param content The task to be done.
     * @param done Whether the task was completed.
     * @param startString Start date of event.
     * @param endString End date of event.
     * @throws InvalidDateFormatException If date field in content is in the incorrect format.
     */
    Event(String content, boolean done, String startString, String endString) throws InvalidDateFormatException {
        super(content, done);
        this.start = DateTimeHelper.parseFormattedDateTime(startString);
        this.end = DateTimeHelper.parseFormattedDateTime(endString);
    }

    /**
     * Checks if the given datetime occurs within the event period.
     *
     * @param datetime the given datetime to check.
     * @return Whether the given datetime occurs within the event period.
     */
    public boolean occursOn(LocalDateTime datetime) {
        return datetime.equals(start)
                || (datetime.isAfter(start) && datetime.isBefore(end))
                || datetime.equals(end);
    }

    @Override
    public void update(String input) throws InvalidCommandInputException {
        System.out.println(input);

        if (!input.matches(".* /from .* /to .*")) {
            throw new InvalidCommandInputException("'/from' or '/to' delimiter does not exist.", input);
        }

        String[] arr = input.split(" /from ");
        String[] startEnd = arr[1].split(" /to ");

        String content = arr[0];
        String start = startEnd[0];
        String end = startEnd[1];

        if (content.length() == 0 || start.length() == 0 || end.length() == 0) {
            throw new InvalidCommandInputException("Empty argument", "event");
        }

        try {
            this.start = DateTimeHelper.parse(start);
            this.end = DateTimeHelper.parse(end);
            updateContent(content);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeHelper.stringify(this.start)
                + " to: " + DateTimeHelper.stringify(this.end) + ")";
    }
}