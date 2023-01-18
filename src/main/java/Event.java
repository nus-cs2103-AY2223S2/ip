public class Event extends Task{
    String startTime;
    String endTime;

    protected Event(String name, String startTime, String endTime) {
        super.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E] " + this.TasktoString()
            + "(from: " + this.startTime 
            + ") (to: " + this.endTime + ")";
    }
}
