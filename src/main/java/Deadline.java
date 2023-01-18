public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description) {
        super(description.split("/by")[0]);
        this.deadline = description.split("/by")[1];
    }
}
