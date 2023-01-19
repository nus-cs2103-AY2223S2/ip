public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[%s][E] %s (from: %s to %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), this.startTime, this.endTime);
    }
}
