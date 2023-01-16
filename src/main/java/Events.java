public class Events extends Task {
    private String startTime;
    private String endTime;

    public Events(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startTime + " : " + endTime + ")";
    }
}
