public class Events extends Task {
    protected String startDate;
    protected String endDate;

    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
