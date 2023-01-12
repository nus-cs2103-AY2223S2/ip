package duke.task;

public class DeadlineTask extends GeneralDukeTask{
    private final String deadline;

    public DeadlineTask(String info, String deadline) {
        super(info, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.deadline + ")";
    }
}