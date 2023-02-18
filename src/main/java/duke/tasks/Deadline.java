package duke.tasks;

import java.time.LocalDate;

import duke.date.DukeDate;

public class Deadline extends Task {
    private static final long serialVersionUID = 8193173341399324817L;
    private LocalDate by;


    public Deadline(String description, String by) {
        super(description);
        this.by = DukeDate.parseDateString(by);
    }

    public boolean checkIfDeadlineActive(String dateString) {
        LocalDate date = DukeDate.parseDateString(dateString);
        boolean isBeforeByDate = date.isBefore(by);
        return isBeforeByDate && !super.isDone;
    }

    @Override
    public String toString() {
        return String.format("  [D]%s (by: %s)", super.toString(),
                DukeDate.convertDateToString(by));
    }
}
