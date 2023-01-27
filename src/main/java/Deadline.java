import java.time.LocalDate;

public class Deadline extends Task{
    LocalDate deadline;

    Deadline(String name, String by) {
        super(name);
        this.deadline = LocalDate.parse(by);
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
