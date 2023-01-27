package duke.task;

public class Deadline extends Task{
    protected String time;

    public Deadline (String description, String time) {
        super(description);
        this.time = time;
    }
    public Deadline (boolean isDone, String description, String time) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    @Override
    public String save() {
        return "D|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + this.time + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + time + ")";
    }
}
