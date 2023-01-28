public class Deadline extends Task{
    private final String taskType = "[D]";
    private String byString;

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (by: " + this.byString + ")";
    }
}
