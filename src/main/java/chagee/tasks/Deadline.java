package chagee.tasks;

import java.time.LocalDate;

import chagee.date.ChageeDate;

/**
 * A class to represent a Deadline.
 */
public class Deadline extends Task {
    private static final long serialVersionUID = 8193173341399324817L;
    private LocalDate by;


    /**
     * A {@code Deadline} constructor.
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = ChageeDate.parseDateString(by);
    }

    /**
     * Checks whether the deadline is still active on {@code dateString}.
     */
    public boolean checkIfDeadlineActive(String dateString) {
        LocalDate date = ChageeDate.parseDateString(dateString);
        boolean isBeforeByDate = date.isBefore(by);
        return isBeforeByDate && !super.isDone;
    }

    @Override
    public String toString() {
        return String.format("  [D]%s (by: %s)", super.toString(),
                ChageeDate.convertDateToString(by));
    }
}
