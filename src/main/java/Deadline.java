public class Deadline extends Task{
    String deadline;
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
        this.type = "[D]";
    }

    public Deadline(String title, String deadline, boolean done) {
        super(title);
        this.deadline = deadline;
        this.type = "[D]";
        this.done = done;
    }

    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
