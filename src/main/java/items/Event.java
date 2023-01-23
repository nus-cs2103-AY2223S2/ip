package items;

public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "]"
                + this.description + "/" + this.startDate + "/" + this.endDate;
    }
}
