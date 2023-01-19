public class Deadline extends Task{
    String deadline;

    Deadline(String name, String by) {
        super(name);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
