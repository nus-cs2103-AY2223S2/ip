package duke;

class Deadline extends Task {

    protected String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    Deadline markAsDone() {
        return new Deadline(this.getDescription(), by, true);
    }
    
    @Override
    Deadline markAsUndone() {
        return new Deadline(this.getDescription(), by, false);
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
