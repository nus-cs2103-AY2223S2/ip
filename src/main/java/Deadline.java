public class Deadline extends Task{
    String deadline;
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
        this.type = "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
