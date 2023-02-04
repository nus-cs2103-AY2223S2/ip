package Duke.Commands.Tasks;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Generates a letter representing the type of task
     *
     * @return a letter representing the type of this task
     */
    public String getTaskClass() {
        return "T";
    }

    private String getFromTime() {
        return this.from;
    }

    private String getToTime() {
        return this.to;
    }

    /**
     * Generates a String to store this task in a local text file
     *
     * @return A representative String that contains data about the current task
     */
    public String generateStorageText() {
        return String.format("%s-%s-%s-%s-%s",
                this.getTaskClass(), this.getStatusIcon(),
                this.getDescription(), this.getFromTime(), this.getToTime());
    }

    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.getTaskClass(), this.getStatusIcon(),
                this.description, this.from, this.to);
    }
}
