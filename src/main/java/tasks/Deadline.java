package tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    private static final long serialVersionUID = 8193173341399324817L;
    LocalDate by;


    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, super.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(super.OUTPUT_DATE_FORMAT));
    }
}
