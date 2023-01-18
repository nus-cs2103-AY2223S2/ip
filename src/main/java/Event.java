public class Event extends Task{
    private String startDate;
    private String endDate;
    public Event(String task,String startDate, String endDate) {
        super(task);
        this.endDate = endDate;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
