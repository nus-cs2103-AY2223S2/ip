public class Deadline extends Task {

    private String end;

    public Deadline(String desc, boolean isDone, String end) {
        super(desc, isDone);
        this.end = end;
    }

    public String statusStringForFile() {
        return String.format("DEADLINE / %s / %s", super.stringFormatForFile(), this.end.trim());
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
