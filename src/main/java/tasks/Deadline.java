package tasks;

public class Deadline extends Task {
    private static final long serialVersionUID = 8193173341399324817L;
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
