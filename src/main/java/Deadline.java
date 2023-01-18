public class Deadline extends Task {
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        setDeadline(deadline);
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    
    private String deadline() {
        return "(by: " + this.deadline + ")";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + this.deadline();
    }
}
