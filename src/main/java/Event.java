/**
* A class that represents an event with starting and ending time
*/
public class Event extends Task {
    private String start;
    private String end;

    /**
    * Initialize an Event object with the given values.
    *
    * @param name The name of the event
    * @param start The starting time of the event
    * @param end The ending time of the event
    * @return An event instance
    */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the Event task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
