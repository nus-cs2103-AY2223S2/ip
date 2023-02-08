package duke.tasks;

import java.time.LocalDate;
import duke.date.DukeDate;

public class Deadline extends Task {
    private static final long serialVersionUID = 8193173341399324817L;
    LocalDate by;


    public Deadline(String description, String by) {
        super(description);
        this.by = DukeDate.parseDateString(by);
    }

    @Override
    public String toString() {
        return String.format("  [D]%s (by: %s)", super.toString(),
                DukeDate.convertDateToString(by));
    }
}
