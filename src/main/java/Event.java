public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String des, String startTime, String endTime) {
        super(des);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getStatusIcon() {
        return String.format("[E]%s (from: %s to: %s)", super.getStatusIcon(), this.startTime, this.endTime);
    }
}