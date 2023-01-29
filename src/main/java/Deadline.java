public class Deadline extends Task {
    protected String dateToComplete;

    public Deadline(String taskInfo, String dateToComplete) {
        super(taskInfo);
        this.dateToComplete = dateToComplete;
    }

    @Override
    public String getTaskInfoStatus() {
        return "[D]" + super.getTaskInfoStatus() + " (by:" + this.dateToComplete + ")";
    }

    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfoStatus() + "/by" + this.dateToComplete;
    }
}
