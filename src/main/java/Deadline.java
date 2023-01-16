public class Deadline extends Task {

    protected String by;

    Deadline(String by, String desc) {
        super(desc);
        this.by = by;
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
