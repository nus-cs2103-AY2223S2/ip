package duke;

public class Deadline extends Task {
    String deadlineTimeString;
    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTimeString = Parser.formatDateString(deadlineTime);
    }

    public String getDeadlineTimeString() {
        return deadlineTimeString;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + "(" + deadlineTimeString + "hrs" + ")";
    }
    public String getSaveString() {
        return String.format("deadline / %s / %s / %s", super.getSaveString(), this.getDescription(), deadlineTimeString);
    }
}
