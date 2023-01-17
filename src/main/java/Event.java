public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTask() {
        return String.format("[E][%s] %s (%s - %s)",
                (super.isDone() ? "X" : " "),
                super.getDescription(),
                this.startTime,
                this.endTime);
    }
}
