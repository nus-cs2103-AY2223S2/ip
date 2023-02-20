package cluck.tasks;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(boolean isMarked, String description, String startTime, String endTime) {
        super(isMarked, description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String makeSaveFormat() {
        return String.format("E|%1$s|%2$s\n", this.isMarked ? "1" : "0", this.description
                + "|" + this.startTime + "|" + this.endTime);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() +
                String.format(" (from: %1$s, to: %2$s)", this.startTime, this.endTime);

    }
}
