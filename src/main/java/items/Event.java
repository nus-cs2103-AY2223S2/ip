package items;

import java.time.LocalDate;

public class Event extends Task{
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, boolean done, LocalDate startDate, LocalDate endDate) {
        super(description, "E", done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String generateStorageForm() {
        return this.getTaskType() + "@" + this.getDescription() + "@" + this.getStatusIcon() + "@"
                + this.startDate + "@" + this.endDate;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "]"
                + this.description + "/" + this.startDate + "/" + this.endDate;
    }
}
