public class Deadline extends Task{
    String deadline;
    Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    protected String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by:%s)", getType(), getStatusIcon(), this.desc, deadline);
    }
}
