public class Deadline extends Task{
    String deadline;

    Deadline(String name, String by) {
        super(name);
        this.deadline = by;
    }

    @Override
    public String storageFormat() {
        return String.join("|", "D", super.storageFormat(), deadline)  + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
