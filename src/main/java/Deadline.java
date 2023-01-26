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
}
