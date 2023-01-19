public class Deadline extends Task {
    protected String dateToComplete;

    public Deadline(String taskInfo, String dateToComplete) {
        super(taskInfo);
        this.dateToComplete = dateToComplete;
    }

    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfo() + " (by:" + dateToComplete + ")";
    }
}
