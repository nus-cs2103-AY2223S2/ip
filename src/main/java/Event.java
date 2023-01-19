public class Event extends Deadline {
    protected String startDate;

    Event(String description, String startDate, String endDate) {
        super(description, endDate);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Event/" + getTaskDescription() + "(from" + startDate + "to" + endDate + ")";
    }
}
