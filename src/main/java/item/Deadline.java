package item;

public class Deadline extends Item {

    /** A date/time for this Deadline */
    private String deadline = "";

    /**
     * Constructor for Deadline item.
     * @param content
     * @param deadline
     */
    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return (this.isDone ? this.MARKED : this.UNMARKED) + " " + this.content
                + " (by: " + this.deadline + ")";
    }
}
