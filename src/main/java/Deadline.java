public class Deadline extends Task {

    public String duration;
    public Deadline(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
    public String getTaskIcon() {
        return "[D]";
    }

    public String currToPrint() {
        return this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription() +
                " (" + this.getDuration() + ")";
    }

}
