public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[D][X] " + this.taskName +
                    " (from: " + this.start + " to: " + this.end + ")";
        } else {
            s = "[D][ ] " + this.taskName  +
                    " (from: " + this.start + " to: " + this.end + ")";
        }
        return s;
    }

}
