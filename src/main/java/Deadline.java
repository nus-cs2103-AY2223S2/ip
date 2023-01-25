public class Deadline extends Task{

    protected String by;
    protected String desc;

    public Deadline(String desc, String by) {
        super(desc);
        this.desc = desc;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String asCSV() {
        return "D," + desc + "," + by;
    }
}
