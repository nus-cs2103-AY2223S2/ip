public class Deadline extends Task {

    private String end;

    public Deadline(String desc, String end) {
        super(desc);
        this.end = end;
    }

    /**
     * overriding the toString function to contain the type of task being created
     * @return string of the deadline being created
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.end + ")";
    }
}
