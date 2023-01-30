public class Events extends Task {

    protected String from;
    protected String to;

    public Events(String description, String from, String to, Boolean isDone) {
        super(description, 'E', isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of this event task
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + "to:" + to + ")";
    }

    /**
     * Returns a string representation of what is saved in the database
     * @return String
     */
    @Override
    public String savedAs() {
        return (super.savedAs() + "|" + this.from + "|" + this.to);
    }
}
