package items;

public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, boolean done, String startDate, String endDate) {
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
