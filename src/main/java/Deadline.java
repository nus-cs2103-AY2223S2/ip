public class Deadline extends Task {
    private final String end;

    private Deadline(String desc, boolean done, String end) {
        super(desc, done);
        this.end = end;
    }
    
    public Deadline(String desc, String end) {
        this(desc, false, end);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.end);
    }

    @Override
    public Deadline markDone() {
        return new Deadline(this.desc, true, this.end);
    }

    @Override
    public Deadline markNotDone() {
        return new Deadline(this.desc, false, this.end);
    }
}
