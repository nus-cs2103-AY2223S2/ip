package duke;

public class Event extends Task {

    String duration;
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, boolean isDone, String duration) {
        super(description, isDone);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
    public String getTaskIcon() {
        return "E";
    }



    public String toSave() {
        return this.getTaskIcon() + SEPARATOR + convertBoolean() + SEPARATOR + this.getDescription()
                + SEPARATOR + this.getDuration();
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]"
                + this.getStatusIcon() + " " + this.getDescription()
                + " (" + this.getDuration() + ")";
    }

}
