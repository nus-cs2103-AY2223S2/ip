public class Events extends Task {
    protected String startDate;
    protected String endDate;
    public Events(String name, String startDate, String endDate) {
        super(name, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String description() {
        return String.format("%s (from: %s to: %s)", super.description(), this.startDate, this.endDate);
    }
}
