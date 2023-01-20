public class Event extends Task{

    private Duration duration;
    private static String taskType = "E";

    public Event(int isCompleted, String taskDescription, Duration duration) {
        super(taskDescription, isCompleted);
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + this.duration.toString();
    }

    @Override
    public String formatForSave() {
        return taskType + "<>" +super.formatForSave() + "<>" + this.duration.formatForSave();
    }
}
