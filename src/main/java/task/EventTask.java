package task;

public class EventTask extends Task {
    private String fromDateTime;
    private String toDateTime;

    public EventTask(String task, String fromDateTime, String toDateTime) {
        super(task);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s, to: %s)",
                super.toString(),
                this.fromDateTime == null ? "[none]" : this.fromDateTime,
                this.toDateTime == null ? "[none]" : this.toDateTime
        );
    }
}
