public class Event extends Task {
    private String start;
    private String end;
    public Event(String taskDescription, String start, String end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toStorageFormatString() {
        return "E|" + (isDone? "1" : "0") + "|" +
                this.taskDescription + "|" +
                this.start + "|" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end +")";
    }

}
