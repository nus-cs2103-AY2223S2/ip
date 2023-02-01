package babe.task;

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

    /**
     * Returns String representation of the Deadline object for data storage.
     * Saves the content of the Deadline, including its isDone status, description,and the deadline date
     * into a String with the delimiter "|" to be written into a data file.
     *
     * @return A String representing this Deadline object.
     */
    public String toSaveFormat() {
        return "D|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + this.deadline;
    };
}


