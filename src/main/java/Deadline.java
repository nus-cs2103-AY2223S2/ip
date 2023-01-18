class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}
