package duke;
public class Deadline extends Task {

    String duration;
    public Deadline(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public Deadline(String description, boolean isDone, String duration) {
        super(description, isDone);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
    public String getTaskIcon() {
        return "D";
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
