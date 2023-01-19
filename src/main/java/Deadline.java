public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " "
                + super.getDescription() + "(by: " + this.by + ")";
    }

    public static Deadline addDeadline(String details) {
        String description = details.substring(0, details.indexOf("/by"));
        String by = details.substring(details.indexOf("/by") + "/by".length() + 1);
        Deadline t = new Deadline(description, by);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        return t;
    }
}