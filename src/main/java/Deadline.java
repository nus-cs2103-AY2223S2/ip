public class Deadline extends Task {
    private String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
