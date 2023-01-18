public class Deadline extends Task {

    private String end;

    public Deadline(String desc, String end) {
        super(desc);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.end + ")";
    }
}
