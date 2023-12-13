package botanic.task;

import java.time.LocalDate;

import botanic.Formatter;

/**
 * Encapsulates the related fields and behavior of an Event task.
 * This class represents a task that starts and ends at a specific date.
 */
public class Event extends Task {
    //Start time
    private LocalDate start;

    //End time
    private LocalDate end;

    /**
     * Instantiates Event with three arguments given.
     *
     * @param name The name of the task.
     * @param start The start date of event.
     * @param end The end date of event.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates Event with 4 arguments given.
     *
     * @param name The name of the task.
     * @param start The start date of event.
     * @param end The end date of event.
     * @param isDone Status of the task.
     */
    public Event(String name, LocalDate start, LocalDate end, boolean isDone) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string with a [E] icon representing this task.
     *
     * @return A string representation of this Event task.
     */
    @Override
    public String toString() {
        String s = Formatter.formatDateForPrint(start);
        String e = Formatter.formatDateForPrint(end);
        String toPrint = String.format("[E]%s (from: %s to: %s)",
                super.toString(), s, e);
        return toPrint;
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this Event task.
     */
    @Override
    public String formatForStorage() {
        String s = Formatter.formatDateForStorage(start);
        String e = Formatter.formatDateForStorage(end);
        return ("E | " + super.formatForStorage() + String.format(" | %s | %s", s, e));
    }

    /**
     * Checks if given date matches with this event's start date or end date.
     *
     * @param dateToFind The given date to find.
     * @return True if date given matches with this event's start or end date, false otherwise.
     */
    @Override
    public boolean hasDate(LocalDate dateToFind) {
        if (start.equals(dateToFind) || end.equals(dateToFind)) {
            return true;
        }
        return false;
    }
}
