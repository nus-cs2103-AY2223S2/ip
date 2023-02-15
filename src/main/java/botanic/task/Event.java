package botanic.task;

import java.time.LocalDate;

import botanic.Formatter;

/**
 * Encapsulates the related fields and behavior of an Event task.
 * Represents a task that starts and ends at a specific date/time.
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
     * @param start The start date/time of event.
     * @param end The end date/time of event.
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
     * @param start The start date/time of event.
     * @param end The end date/time of event.
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
        String s = Formatter.formatDateForPrint(this.start);
        String e = Formatter.formatDateForPrint(this.end);
        String toPrint = String.format("[E]%s (from: %s to: %s)",
                super.toString(), s, e);
        return toPrint;
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this task.
     */
    @Override
    public String formatForStorage() {
        String s = Formatter.formatDateForStorage(this.start);
        String e = Formatter.formatDateForStorage(this.end);
        return ("E | " + super.formatForStorage() + String.format(" | %s | %s", s, e));
    }

    /**
     * Searches for given date in the task description.
     *
     * @param dateToFind The given date to find.
     * @return True if date matches start or end date, false otherwise.
     */
    @Override
    public boolean containDate(LocalDate dateToFind) {
        if (this.start.equals(dateToFind) || this.end.equals(dateToFind)) {
            return true;
        }
        return false;
    }
}
