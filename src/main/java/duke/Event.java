package duke;

/***
 * Event class type, a subclass of Task
 */
public class Event extends Task {
    final String ICON = "[E]";
    String from;
    String to;

    /***
     *
     * @param details details of the Task
     * @param from the starting date of the task
     * @param to the end date of the task
     */
    public Event(String details, String from, String to) {
        super(details);
        this.from = from.replace("from", "");
        this.to = to.replace("to", "");
    }

    /***
     *
     * @param details details of the task
     */
    public Event(String details) {
        super(details);
    }

    /***
     *
     * @return the description of the task in String type indicating as an Event type
     */
    @Override
    public String toString() {
        return ICON + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}
