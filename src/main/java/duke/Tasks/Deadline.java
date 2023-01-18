package duke.Tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        System.out.println(" " + "____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + toString());
        System.out.println(" Now you have " + taskNum + " tasks in the list.");
        System.out.println(" " + "____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (" + "by: " + this.by + ")";
    }
}
