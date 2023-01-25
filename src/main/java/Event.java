public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toText() {
        return "E" + "|" + super.toText() + "|" + startDate + "|" + endDate;
    };
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDate + "to: " + endDate + ")";
    }
}
