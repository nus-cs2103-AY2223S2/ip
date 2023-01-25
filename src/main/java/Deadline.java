public class Deadline extends Task{
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toLog() {
        return "D" + super.toLog() + "| " + this.deadline + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}
