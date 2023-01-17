public class Deadline extends Task {

    private String end;

    public Deadline(String desc, String end) {
        super(desc);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + super.getDescription() + " (by:" + this.end + ")";
    }
}
