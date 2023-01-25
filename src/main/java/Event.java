public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String title, String startTime, String endTime, boolean isDone) {
        super(title, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toDiskFormat() {
        return String.format("D|%d|%s|%s|%s", super.getIsDone() ? 1 : 0, super.getTitle(),
                this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[%s][E] %s (from: %s to %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), this.startTime, this.endTime);
    }
}
