package tasks;

public class Deadline extends Task{
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String toSaveFormat() {
        return String.format("D,%s,%s", this.name, this.getDeadline());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.deadline + ")";
    }
}
