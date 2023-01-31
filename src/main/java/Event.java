public class Event extends Task{
    String taskDescription;
    String startDate;
    String endDate;

    public Event(String taskString) {
        super(taskString.substring(6, taskString.indexOf("/") - 1) + " (from: " +
                taskString.substring(taskString.indexOf("/") + 6, taskString.lastIndexOf("/") - 1) +
                " to: " + taskString.substring(taskString.lastIndexOf("/") + 4) + ")");

        taskDescription = taskString.substring(6, taskString.indexOf("/") - 1);
        startDate = taskString.substring(taskString.indexOf("/") + 6, taskString.lastIndexOf("/") - 1);
        endDate = taskString.substring(taskString.lastIndexOf("/") + 4);
    }

    @Override
    public String getTask() {
        return this.taskDescription;
    }

    public String getTimeline() {
        return this.startDate + " - " + this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
