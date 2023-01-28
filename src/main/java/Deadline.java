public class Deadline extends Task {

    private String endTime;

    public Deadline(String s, String endTime) {
        super(s);
        this.endTime = endTime;
    }

    public Deadline(Boolean isTaskDone, String taskDetails, String taskDate) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
        this.endTime = taskDate;
    }

    @Override
    public String toString() {
        if (!taskDone) {
            return "[D][ ] " + this.taskName + " (by: " + this.endTime + ")";
        }
        return "[D][X] " + this.taskName + " (by: " + this.endTime + ")";
    }

}
