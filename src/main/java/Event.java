
/**
 * Contains information of an event
 * Contains description and duration of the event
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates an event object
     *
     * @param description The description of the event
     * @param from Starting time of the event
     * @param to Ending time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a deadline object from user input
     * Handles exceptions
     *
     * @param input Input from user
     * @return Deadline Task object
     * @throws DukeException If description of the event is empty
     * @throws DukeException If start time of the event is empty
     * @throws DukeException If end time of the event is empty
     */
    public static Event generate(String input) throws DukeException {
        // Cleans input command
        input = input.trim();

        // Checks format of input command
        int indexDesc = input.indexOf(" ");
        int indexFrom = input.indexOf(" /from ");
        int indexTime = input.indexOf(" /to ");
        if (indexDesc < 0) {
            throw new DukeException("Event", "Empty description");
        } else if (indexFrom < 0) {
            throw new DukeException("Event", "Empty From Time");
        } else if (indexTime < 0) {
            throw new DukeException("Event", "Empty To Time");
        }

        // Cleans and checks variables
        String description = input.substring(indexDesc + 1, indexFrom).trim();
        String timeFrom = input.substring(indexFrom + 7, indexTime).trim();
        String timeTo = input.substring(indexTime + 5).trim();
        if (description.equals("")) {
            throw new DukeException("Event", "Empty description");
        } else if (timeFrom.equals("")) {
            throw new DukeException("Event", "Empty From Time");
        } else if (timeTo.equals("")) {
            throw new DukeException("Event", "Empty To Time");
        }

        return new Event(description, timeFrom, timeTo);
    }

    /**
     * Returns type of task, completion status, description, and duration of
     * the event
     *
     * @return Type of task, completion status, description, and duration of
     * the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from
                + " to: " + to + ")";
    }
}
