package tasks;

import java.time.LocalDate;

public class Event extends Task {
    private static final long serialVersionUID = 7664438936982546960L;
    LocalDate from;
    LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from, super.INPUT_DATE_FORMAT);
        this.to = LocalDate.parse(to, super.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(super.OUTPUT_DATE_FORMAT), to.format(super.OUTPUT_DATE_FORMAT));
    }
}
