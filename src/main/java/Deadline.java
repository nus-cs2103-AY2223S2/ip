public class Deadline extends Task{

    protected String by;
    protected String desc;

    public Deadline(String desc, String by) {
        super(desc);
        this.desc = desc;
        this.by = by;
    }

    public Deadline(String desc, String by, boolean b) {
        super(desc, b);
        this.desc = desc;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String asCSV() {
        if (super.isDone) {
            return "D,1" + desc + "," + by;
        } else {
            return "D,0" + desc + "," + by;
        }
    }
}
