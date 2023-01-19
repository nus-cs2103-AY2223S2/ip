package duke;

public class Event extends Task {
    protected String startTime;
    protected String endTime;
    public Event(String description, String startTime, String endTime) throws DukeException {
        super(description.trim());
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
        if (this.description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (this.startTime.equals("") || this.endTime.equals("")) {
            throw new DukeException("The start and/or end time of a deadline cannot be empty.");
        }
    }
    /**
     * Represent duke.Event as a string
     * @return String representation of a duke.Event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }
}
