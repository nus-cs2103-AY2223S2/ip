public class EventTask extends Task {

    private static final String EVENT_SYMBOL = "E";
    private String startTime;
    private String endTime;

    public EventTask(String description, String startTime, String endTime) {
        super(description, EVENT_SYMBOL);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + "|" + startTime + "|" + endTime;
    }
}