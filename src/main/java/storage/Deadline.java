package storage;

public class Deadline extends Task {

    protected String by;

    /**
     * Creates a new deadline with the specified description
     *
     * @param description The specified description
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
