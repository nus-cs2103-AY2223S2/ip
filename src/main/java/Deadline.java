public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, Boolean isDone) {
        super(description, 'D', isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline task
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }


    /**
     * Returns a string representation of what is saved in the database
     * @return String
     */
    @Override
    public String savedAs() {
        return (super.savedAs() + "|" + this.by);
    }
}