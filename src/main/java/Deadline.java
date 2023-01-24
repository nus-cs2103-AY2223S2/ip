public class Deadline extends Task {
    private String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline createDeadline(String desc) {
        String[] deadlineArr = desc.split("/", 2);
        String deadlineDesc = deadlineArr[0].trim();
        String by = deadlineArr[1].substring(3);
        return new Deadline(deadlineDesc, by);
    }
}
