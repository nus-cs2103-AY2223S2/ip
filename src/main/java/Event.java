public class Event extends Task {

    private String startTime;
    private String endTime;

    public Event(String s, String startTime, String endTime) {
        super(s);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (taskDone == false) {
            return "[E][ ] " + this.taskName
                + " (from: " + this.startTime + " to: " + this.endTime + ")";
        }
        return "[E][X] " + this.taskName 
            + " (from: " + this.startTime + " to: " + this.endTime;
    }

}
