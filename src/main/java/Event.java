/**
 * Represents an Event, which is a type of Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String startDatetime;
    protected String endDatetime;

    public Event(String description) throws DukeException {
        super(description.split("/from")[0]);
        try {
            String datetimes = description.split("/from")[1];
            this.startDatetime = datetimes.split("/to")[0];
            this.endDatetime = datetimes.split("/to")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
    }

    /**
     * Returns the String representation of an Event.
     *
     * @return  String representation of an Event in this format: [E][<status>] <description> (from: <start date/time>
     * to: <end date/time>).
     */
    @Override
    public String toString() {
        return String.format("[E][%c] %s(from:%sto:%s)", this.getStatusIcon(), this.description
                , this.startDatetime, this.endDatetime);
    }
}
