package seedu.duke.tasks;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    private static final String PREFIX = "E";

    public Event(String desc, LocalDate from, LocalDate to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String save() {
        StringBuilder response = new StringBuilder("");
        response.append(getPrefix() + ",");
        response.append(isDone + ",");
        response.append(description + ",");
        response.append(from + ",");
        response.append(to + "\n");
        return response.toString();
    }

    @Override
    public String toString(){
        return super.toString()
                + " (from: " + this.from + ", to: "
                + this.to + ")";
    }
}
