public class Deadline extends Task {
    private String name;
    private boolean isDone;
    private String by;
    public Deadline(String name, String by) {
        super(name, false);
        this.name = name;
        this.isDone = false;
        this.by = by;
    }

    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.name = name;
        this.isDone = isDone;
        this.by = by;
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return name + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
