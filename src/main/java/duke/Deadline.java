package duke;

public class Deadline extends Task {
    String deadlineTime;
    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = Parser.parseDate(deadlineTime);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + deadlineTime + "hrs" + ")";
    }
    public String saveString() {
        return String.format("deadline / %s / %s / %s", super.saveString(), this.getDescription(), deadlineTime);
    }
}
