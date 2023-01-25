public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean done, String by) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | by: " + by;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Deadline) {
            Deadline x = (Deadline) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }
}

