package task;

public class Deadline extends Task {

    /** A date/time for this Deadline */
    private String deadline = "";

    /**
     * Constructor for Deadline item.
     *
     * @param content Description of this Deadline.
     * @param deadline Date for the deadline of this item.
     */
    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return (this.isDone ? this.MARKED : this.UNMARKED) + " " + this.description
                + " (by: " + this.deadline + ")";
    }
}
