public class Event extends Task {

    public String duration;
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
    public String getTaskIcon() {
        return "[E]";
    }

    public String currToPrint() {
        return this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription() +
                " (" + this.getDuration() + ")";
    }

}
