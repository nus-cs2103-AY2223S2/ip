package duke.task;

public class EventTask extends GeneralDukeTask{
    private final String from;
    private final String to;

    public EventTask(String info, String from, String to) {
        super(info, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String storageString() {
        String status;
        if (this.getStatus()) {
            status = "[X] | ";
        } else {
            status = "[ ] | ";
        }
        return "[D] | " + status + this.getInformation() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
