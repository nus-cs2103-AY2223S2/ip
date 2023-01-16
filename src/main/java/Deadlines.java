public class Deadlines extends Task {
    private String deadLine;

    public Deadlines(String taskName, String deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + deadLine + ")";
    }
}
