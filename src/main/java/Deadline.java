public class Deadline extends Task{
    protected String by;
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }
    @Override
    public String toFileString() {
        String mark = this.isDone()? "1": "0";
        return String.format("D | %s | %s | %s", mark, this.getDescription(), this.by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
