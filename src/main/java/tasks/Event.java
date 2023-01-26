package tasks;

public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String toSaveFormat() {
        return String.format("E,%s,%s,%s", this.name, this.getStartDate(), this.getEndDate());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(Starts from " + this.startDate + "; ends at " + this.endDate + ")";
    }
}
