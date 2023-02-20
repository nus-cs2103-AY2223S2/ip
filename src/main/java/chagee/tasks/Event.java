package chagee.tasks;

import java.time.LocalDate;

import chagee.date.ChageeDate;

/**
 * A class to represent an Event.
 */
public class Event extends Task {
    private static final long serialVersionUID = 7664438936982546960L;
    private LocalDate from;
    private LocalDate to;

    /**
     * An {@code Event} constructor.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = ChageeDate.parseDateString(from);
        this.to = ChageeDate.parseDateString(to);

    }

    /**
     * Checks if this event is still incomplete and whether it lands on {@code dateString}.
     *
     * @param dateString
     * @return Whether this task is
     */
    public boolean checkIfEventActiveOnDate(String dateString) {
        LocalDate date = ChageeDate.parseDateString(dateString);
        boolean isAfterStartingDate = date.isAfter(from);
        boolean isBeforeEndDate = date.isBefore(to);
        return isAfterStartingDate && isBeforeEndDate && !super.isDone;
    }

    @Override
    public String toString() {
        return String.format("  [E]%s (from: %s to: %s)", super.toString(),
                ChageeDate.convertDateToString(from), ChageeDate.convertDateToString(to));
    }
}
