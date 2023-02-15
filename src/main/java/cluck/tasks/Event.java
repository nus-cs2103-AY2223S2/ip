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
        return this.isMarked
                ? String.format("[E][X] %1$s (from:%2$s to:%3$s)", this.description, this.startTime, this.endTime)
                : String.format("[E][ ] %1$s (from:%2$s to:%3$s)", this.description, this.startTime, this.endTime);
    }
}
