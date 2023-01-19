public class Deadline extends Task {

    protected String by;
    public Deadline(String desc) {

        super(desc.substring(0, desc.indexOf("/by") - 1));
        this.by = desc.substring(desc.indexOf("/by") + 4);

    }

    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", by);
    }

}