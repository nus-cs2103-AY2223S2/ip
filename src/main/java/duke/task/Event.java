package duke.task;

public class Event extends Task{
    protected String time;

    public Event (String description, String time) {
        super(description);
        this.time = time;
    }

    public Event (boolean isDone, String description, String time) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String save() {
        return "E|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + this.time + "\n";
    }

    @Override
    public String toString () {
        return "[E]" + super.toString() + " (" + time + ")";
    }
}
