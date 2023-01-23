public class Deadline extends Task {
    private String deadline;

    public Deadline(String des, String deadline) {
        super(des);
        this.deadline = deadline;
    }

    @Override
    public String getStatusIcon() {
        return String.format("[D]%s | BY: %s", super.getStatusIcon(), this.deadline);
    }

    @Override
    public String encode() {
        return String.format("%s ### %s ### %s", "deadline", super.encode(), this.deadline);
    }
}