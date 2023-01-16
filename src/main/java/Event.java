public class Event extends Task {

    private String startTime;
    private String endTime;

    public Event(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s (from: %s to: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startTime,
                this.endTime));
    }

    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s (from: %s to: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startTime,
                this.endTime));
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startTime,
                this.endTime);
    }
}
