public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + by + ")";
    }

    public String saveFormat(){
        return String.format("D | %s | %s", super.saveFormat(), this.by);
    }
}

