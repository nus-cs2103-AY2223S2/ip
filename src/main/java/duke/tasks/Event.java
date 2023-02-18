package duke.tasks;

import java.time.LocalDate;

import duke.date.DukeDate;

public class Event extends Task {
    private static final long serialVersionUID = 7664438936982546960L;
    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DukeDate.parseDateString(from);
        this.to = DukeDate.parseDateString(to);

    }

    public boolean checkIfEventActiveOnDate(String dateString) {
        LocalDate date = DukeDate.parseDateString(dateString);
        boolean isAfterStartingDate = date.isAfter(from);
        boolean isBeforeEndDate = date.isBefore(to);
        return isAfterStartingDate && isBeforeEndDate && !super.isDone;
    }

    @Override
    public String toString() {
        return String.format("  [E]%s (from: %s to: %s)", super.toString(),
                DukeDate.convertDateToString(from), DukeDate.convertDateToString(to));
    }
}
