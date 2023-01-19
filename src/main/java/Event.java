public class Event extends Task {
    protected String startDate, endDate;

    public Event(String taskInfo, String startDate, String endDate) {
        super(taskInfo);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfo() + "(from:" + startDate + "to:" + endDate + ")";
    }
}
